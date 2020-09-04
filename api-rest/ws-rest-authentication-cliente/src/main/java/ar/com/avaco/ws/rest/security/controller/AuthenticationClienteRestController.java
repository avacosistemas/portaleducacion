package ar.com.avaco.ws.rest.security.controller;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import ar.com.avaco.model.ClienteUserDetailsDTO;
import ar.com.avaco.ws.rest.dto.ErrorResponse;
import ar.com.avaco.ws.rest.dto.JwtAuthenticationRequest;
import ar.com.avaco.ws.rest.dto.JwtAuthenticationResponse;
import ar.com.avaco.ws.rest.security.exception.AuthenticationException;
import ar.com.avaco.ws.rest.security.service.ClienteEPPortalService;
import ar.com.avaco.ws.rest.security.util.JwtTokenUtil;

@RestController
public class AuthenticationClienteRestController {

	@Value("${jwt.header}")
	private String tokenHeader;

	private AuthenticationManager authenticationManager;

	private JwtTokenUtil jwtTokenUtil;

	private ClienteEPPortalService clienteEPService;
	
	private static final String CHANGE_PASSWORD_REQUIRED = "CHANGE_PASSWORD_REQUIRED";
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletRequest request)
			throws AuthenticationException {
		
		request.setAttribute("userNameToAuth", authenticationRequest.getUsername());
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		// Reload password post-security so we can generate the token
		final UserDetails userDetails = clienteEPService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);

		// Return the token
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		ClienteUserDetailsDTO user = (ClienteUserDetailsDTO) clienteEPService.loadUserByUsername(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public ResponseEntity<?> userAuthenticationToken(HttpServletRequest request) {
		String authToken = request.getHeader(tokenHeader);
		final String token = authToken.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserDetails loadUserByUsername = clienteEPService.loadUserByUsername(username);
		ClienteUserDetailsDTO clienteUDDTO = (ClienteUserDetailsDTO) loadUserByUsername; //ClienteUserDetailsFactory.create((Cliente)loadUserByUsername);
		return ResponseEntity.ok(clienteUDDTO);
	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	@ExceptionHandler(CredentialsExpiredException.class)
	public ResponseEntity<ErrorResponse> subhandleException(final CredentialsExpiredException ex,WebRequest wr) {
		ex.printStackTrace();
		
		String tempUserName=(String) wr.getAttribute("userNameToAuth",0);
		final UserDetails userDetails = clienteEPService.loadUserByUsername(tempUserName);
		final String token = jwtTokenUtil.generateToken(userDetails);
		
        return new ResponseEntity<ErrorResponse>(getResponse(ex, CHANGE_PASSWORD_REQUIRED, token), HttpStatus.CONFLICT);
	}
	
	private ErrorResponse getResponse(final Exception ex,String status, String token) {
		ErrorResponse response = getErrorResponse(ex);
		response.setData(new JwtAuthenticationResponse(token));
		response.setStatus(status);
		return response;
	}

	private ErrorResponse getErrorResponse(final Exception ex) {
		ErrorResponse response = new ErrorResponse();
		if(ex.getMessage() != null && !ex.getMessage().isEmpty()) {
			response.setMessage(ex.getMessage());
		}
		
		if(ex.getCause() != null) {
			response.setError(ex.getCause().getMessage());			
		}
		return response;
	}
	/**
	 * Authenticates the user. If something is wrong, an
	 * {@link AuthenticationException} will be thrown
	 */
	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("ClienteDTO is disabled!", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("Bad credentials!", e);
		} catch (CredentialsExpiredException e) {
			throw new CredentialsExpiredException("Credentials Expired!", e);
		}
	}

	@Resource(name = "jwtTokenUtil")
	public void setJwtTokenUtilManager(JwtTokenUtil jwtTokenUtil) {
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Resource(name = "clienteEPService")
	public void setClienteEPService(ClienteEPPortalService clienteEPService) {
		this.clienteEPService = clienteEPService;
	}

	@Resource(name = "authenticationManager")
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
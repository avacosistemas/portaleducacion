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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ar.com.avaco.arc.sec.exception.NuclearJSecurityException;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Identificacion;
import ar.com.avaco.educacion.domain.entities.cliente.TipoIdentificacion;
import ar.com.avaco.educacion.service.alumno.AlumnoService;
import ar.com.avaco.model.ClienteUserDetailsDTO;
import ar.com.avaco.ws.rest.dto.ErrorResponse;
import ar.com.avaco.ws.rest.dto.JwtAuthenticationRequest;
import ar.com.avaco.ws.rest.dto.JwtAuthenticationResponse;
import ar.com.avaco.ws.rest.security.exception.AuthenticationException;
import ar.com.avaco.ws.rest.security.service.ClienteEPPortalService;
import ar.com.avaco.ws.rest.security.util.HttpUtil;
import ar.com.avaco.ws.rest.security.util.JwtTokenUtil;

@RestController
public class AuthenticationClienteRestController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Value("${upcn_client_id}")
	private String upcnClientId;
	
	@Value("${upcn_client_secret}")
	private String upcnClientSecret;
	
	@Value("${upcn_uri_callback}")
	private String upcnUriCallBack;
	
	@Value("${upcn_url}")
	private String upcnUrl;
		
	@Value("${upcn_jwk_url}")
	private String upcnJwkYUrl;
	
	static final String UPCN_USERNAME_PREFIX = "user_upcn_";
	
	private AuthenticationManager authenticationManager;

	private JwtTokenUtil jwtTokenUtil;

	private ClienteEPPortalService clienteEPService;
	
	private AlumnoService alumnoService;
	
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
	
	@RequestMapping(value = "/authUpcn", method = RequestMethod.GET)
	public ResponseEntity<?> createAuthenticationTokenFromUPCN( HttpServletRequest request)
			throws AuthenticationException {			
		
		String code=(String) request.getParameter("code");
		
		String id_usuario=null;
		String username=null;
		String access_token=null;
		String id_token=null;
		
		if (code!=null) {
			String parameters="grant_type=authorization_code&code="+HttpUtil.urlEncode(code)+"&client_id="+HttpUtil.urlEncode(upcnClientId)+
					"&client_secret="+HttpUtil.urlEncode(upcnClientSecret)+"&redirect_uri="+HttpUtil.urlEncode(upcnUriCallBack);
			String response=HttpUtil.postURL(upcnUrl+"/token", parameters, "application/x-www-form-urlencoded");
			if (response!=null) {
				JsonObject jsonObject = (JsonObject )new JsonParser().parse(response);
				access_token=jsonObject.get("access_token")!=null?jsonObject.get("access_token").getAsString():null;
				id_token=jsonObject.get("id_token")!=null?jsonObject.get("id_token").getAsString():null;
				id_usuario = jwtTokenUtil.getUsernameFromTokenWithJwk(id_token, upcnJwkYUrl);
				username = UPCN_USERNAME_PREFIX + id_usuario;
			} else {
				throw new AuthenticationException("Error de Autenticacion contra UPCN", null);
			}
		} else {
			throw new AuthenticationException("Error de Autenticacion contra UPCN", null);
		}
				

		UserDetails userDetails = null;
		
		// Reload password post-security so we can generate the token
		try {
			userDetails = clienteEPService.loadUserByUsername(username);	
		} catch (UsernameNotFoundException usernameNotFoundException) {
			String authorization="Bearer "+access_token;
			String response=HttpUtil.getURL(upcnUrl+"/me", authorization);
			if (response!=null) {
				JsonObject jsonObject = (JsonObject )new JsonParser().parse(response);
				String nombreCompleto=jsonObject.get("nombre_completo")!=null?jsonObject.get("nombre_completo").getAsString():null;
				String cuil=jsonObject.get("cuil")!=null?jsonObject.get("cuil").getAsString():null;
				String email=jsonObject.get("email")!=null?jsonObject.get("email").getAsString():null;				
				String telefonoFijo=!(jsonObject.get("telefono_fijo") instanceof JsonNull)?jsonObject.get("telefono_fijo").getAsString():null;
				String telefonoMovil=!(jsonObject.get("telefono_movil") instanceof JsonNull)?jsonObject.get("telefono_movil").getAsString():null;
							
				try {
					Alumno alumno=new Alumno();
					alumno.setUsername(username);
					alumno.setEmail(email);
					if (nombreCompleto!=null && nombreCompleto.contains(" ")) {
						alumno.setNombre(nombreCompleto.substring(0,nombreCompleto.lastIndexOf(' '))); 
						alumno.setApellido(nombreCompleto.substring(nombreCompleto.lastIndexOf(' ')+1, nombreCompleto.length())); 	
					}
					 
					
					Identificacion identificacion=new Identificacion();
					identificacion.setNumero(cuil.substring(2, 10)); 
					identificacion.setTipo(TipoIdentificacion.DNI);
					identificacion.setCliente(alumno);
					
					alumno.setIdentificacion(identificacion);
					
					Contacto contacto=new Contacto();
					contacto.setTelefonoFijo(telefonoFijo);
					contacto.setTelefonoMovil(telefonoMovil);
					contacto.setCliente(alumno);
					
					alumno.setContacto(contacto);
					alumno.setPassword("UNDEFINED");
					
					alumno.setSistemaExterno(true);
					alumno.setIdSistemaExterno(id_usuario);
					
					alumnoService.registrarAlumno(alumno);
					
					userDetails = clienteEPService.loadUserByUsername(username);
					
				} catch (BusinessException e) {
					throw new AuthenticationException("No se pudo crear el usuario a partir de los datos de UPCN",e);
				}
				
			} else {
				throw new AuthenticationException("Error de Autenticacion contra UPCN", null);
			}
		}
		
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

		if (clienteEPService.isSistemaExterno(username)) {
			throw new NuclearJSecurityException("Usuarios de sistemas externos no pueden por esta via. Deben utilizar la pagina institucional de su empresa.");
		}
		
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
	
	@Resource(name = "alumnoService")
	public void setAlumnoService(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}

	@Resource(name = "authenticationManager")
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
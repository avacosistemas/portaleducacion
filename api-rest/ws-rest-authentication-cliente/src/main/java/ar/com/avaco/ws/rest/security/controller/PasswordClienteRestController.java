package ar.com.avaco.ws.rest.security.controller;

import javax.annotation.Resource
;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.ws.rest.security.dto.PassworResetClienteDTO;
import ar.com.avaco.ws.rest.security.dto.UpdatePasswordDTO;
import ar.com.avaco.ws.rest.security.service.ClienteEPPortalService;
import ar.com.avaco.ws.rest.dto.JSONResponse;
import ar.com.avaco.ws.rest.service.FunctionBusiness;

@RestController
public class PasswordClienteRestController {

	private ClienteEPPortalService clienteEPService;

	@RequestMapping(value = "/password/reset/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> reset(@RequestBody PassworResetClienteDTO dto) throws BusinessException {
		clienteEPService.sendMissingPassword(dto.getUsername());
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setData(null);
		jsonResponse.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(jsonResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/password/update/", method = RequestMethod.POST)
    public  ResponseEntity<JSONResponse> resetPassword(HttpServletRequest request, @RequestBody UpdatePasswordDTO updatePassword) throws BusinessException {
    	return executeProcess("update-password", Void -> {this.clienteEPService.updatePassword(updatePassword); return null;});
    }
	
	public <R> ResponseEntity<JSONResponse> executeProcess(String processName, FunctionBusiness<Void, R> function) throws BusinessException {
    	HttpStatus httpStatus = HttpStatus.OK;
    	JSONResponse response = getResponseOK(function.apply(null));
        return new ResponseEntity<JSONResponse>(response, httpStatus);
    }
	
	protected <T> JSONResponse  getResponseOK(T data) {
		JSONResponse response = new JSONResponse();
        response.setData(data);
   		response.setStatus(JSONResponse.OK);
		return response;
	}

	@Resource(name= "clienteEPService")
	public void setClienteEPService(ClienteEPPortalService clienteEPService) {
		this.clienteEPService = clienteEPService;
	}
    
}

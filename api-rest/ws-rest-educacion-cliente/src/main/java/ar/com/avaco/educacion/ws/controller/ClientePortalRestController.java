package ar.com.avaco.educacion.ws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.educacion.domain.entities.cliente.Cliente;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ClientePortalRestController  {

//	extends AbstractDTORestController<ClienteListadoDTO, Long, ClienteEPService>
	
	@RequestMapping(value = "/cliente/tipo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getTipoCliente(@PathVariable("id") Long id) throws Exception {
		Cliente principal = (Cliente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (principal.getId().equals(id)) {
			response.setData(principal.getTipoCliente());
			response.setStatus(JSONResponse.OK);	
		} else {
			response.setStatus(JSONResponse.ERROR);	
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
//	@Resource(name = "clienteEPService")
//	public void setService(ClienteEPService clienteEPService) {
//		super.service = clienteEPService;
//	}

}
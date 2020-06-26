package ar.com.avaco.educacion.ws.educacion.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.educacion.dto.ClienteCompletoDTO;
import ar.com.avaco.educacion.ws.educacion.dto.ClienteListadoDTO;
import ar.com.avaco.educacion.ws.educacion.dto.ContactoDTO;
import ar.com.avaco.educacion.ws.educacion.service.ClienteEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ClienteRestController
		extends AbstractDTORestController<ClienteListadoDTO, Long, ClienteEPService> {


	@RequestMapping(value = "/clientes/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listClientesListado() throws Exception  {
		List<ClienteListadoDTO> listClientesListado = this.service.listClientesListado();
    	JSONResponse response = new JSONResponse();
		response.setData(listClientesListado);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getCompleto(@PathVariable("id") Long id) throws Exception {
		ClienteCompletoDTO clienteCompleto = this.service.getClienteCompleto(id);
		JSONResponse response = new JSONResponse();
		response.setData(clienteCompleto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/clientes/resetPassword/{id}", method = RequestMethod.GET)
	public ResponseEntity<JSONResponse> resetPassword(@PathVariable("id") Long id) throws BusinessException {
		this.service.resetPassword(id);
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);	
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> update(@PathVariable("id") Long id, @RequestBody ClienteCompletoDTO cliente) throws BusinessException {
		ClienteCompletoDTO updateClienteCompleto = service.updateClienteCompleto(id, cliente);
		JSONResponse response = new JSONResponse();
		response.setData(updateClienteCompleto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
		return null;
	}

	@RequestMapping(value = "/clientes/", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@RequestParam Map<String, String> customQuery) throws BusinessException {
		return null;
	}

	@RequestMapping(value = "/clientes/datosPersonales/validar/{id}", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> validarDatosPersona(@PathVariable("id") Long id, @RequestBody ClienteCompletoDTO clienteDTO) throws Exception {
		return super.executeProcess("validar-datospersonales", Void -> { service.validarActualizacionCliente(clienteDTO); return null;});
	}

	@RequestMapping(value = "/clientes/contacto/validar/{id}", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> validarContacto(@PathVariable("id") Long id, @RequestBody ContactoDTO contactoDTO) throws Exception {
		return super.executeProcess("validar-contacto", Void -> { service.validarContacto(contactoDTO); return null;});
	}
	
	@Resource(name = "clienteEPService")
	public void setService(ClienteEPService clienteEPService) {
		super.service = clienteEPService;
	}

}
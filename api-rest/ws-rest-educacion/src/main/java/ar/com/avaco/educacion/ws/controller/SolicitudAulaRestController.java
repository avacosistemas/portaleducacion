package ar.com.avaco.educacion.ws.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.RechazoSolicitudDTO;
import ar.com.avaco.educacion.ws.dto.SolicitudAulaDTO;
import ar.com.avaco.educacion.ws.service.SolicitudAulaEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class SolicitudAulaRestController extends AbstractDTORestController<SolicitudAulaDTO, Long, SolicitudAulaEPService> {

	
	@RequestMapping(value = "/solicitudAula", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listSolicitudes() throws Exception  {
    	JSONResponse response = new JSONResponse();
		response.setData(this.service.list());
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/solicitudAulaFinalizada", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listSolicitudesFinalizadas() throws Exception  {
    	JSONResponse response = new JSONResponse();
		response.setData(this.service.listFinalizadas());
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/solicitudAula/aprobar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> aprobarSolicitud(@PathVariable("id") Long id) throws BusinessException {
		service.aprobar(id);
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/solicitudAula/rechazar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> rechazarSolicitud(@PathVariable("id") Long id, @RequestBody RechazoSolicitudDTO comentario) throws BusinessException {
		service.rechazar(id, comentario.getSummary());
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);	
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@Override
	@Resource(name = "solicitudAulaEPService")
	public void setService(SolicitudAulaEPService service) {
		this.service = service;
	}

}

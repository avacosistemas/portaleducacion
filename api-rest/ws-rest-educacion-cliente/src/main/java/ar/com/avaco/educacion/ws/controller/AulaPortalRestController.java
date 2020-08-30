package ar.com.avaco.educacion.ws.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.AlumnoPerfilDTO;
import ar.com.avaco.educacion.ws.dto.AulaAlumnoDTO;
import ar.com.avaco.educacion.ws.dto.AulaProfesorDTO;
import ar.com.avaco.educacion.ws.dto.URLAulaDTO;
import ar.com.avaco.educacion.ws.service.AlumnoPerfilEPService;
import ar.com.avaco.educacion.ws.service.AulaPortalEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.controller.AbstractRestBaseController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class AulaPortalRestController extends AbstractRestBaseController {
	
	private AulaPortalEPService service;
	
	@RequestMapping(value = "/aula/profesor/crearunirse/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> abrirClase(@RequestBody AulaProfesorDTO aulaProfesorDTO) throws BusinessException {
		URLAulaDTO newAulaDto = service.abrirClase(aulaProfesorDTO);
		JSONResponse response = new JSONResponse();
		response.setData(newAulaDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/aula/alumno/unirse/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> unirseClaseAlumno(@RequestBody AulaAlumnoDTO aulaAlumnoDTO) throws BusinessException {
		URLAulaDTO newAulaDto = service.unirseClase(aulaAlumnoDTO);
		JSONResponse response = new JSONResponse();
		response.setData(newAulaDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);	
	}
		
	//Service
	@Resource(name = "aulaPortalEPService")
	public void setService(AulaPortalEPService alumnoEPService) {
		this.service = alumnoEPService;
	}

}

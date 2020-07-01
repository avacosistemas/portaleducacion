package ar.com.avaco.educacion.ws.controller;

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
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.ProfesorDTO;
import ar.com.avaco.educacion.ws.service.ProfesorEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ProfesorRestController extends AbstractDTORestController<ProfesorDTO, Long, ProfesorEPService> {

	
	@RequestMapping(value = "/profesores/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listProfesores() throws Exception  {
		List<ProfesorDTO> listProfesores = this.service.listProfesores();
    	JSONResponse response = new JSONResponse();
		response.setData(listProfesores);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/profesores/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getProfesor(@PathVariable("id") Long id) throws Exception {
		ProfesorDTO profesor = this.service.getProfesor(id);
		JSONResponse response = new JSONResponse();
		response.setData(profesor);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profesores/habilitar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> habilitarProfesor(@PathVariable("id") Long id) throws BusinessException {
		ProfesorDTO updateProfesor = service.bloquearHabilitarProfesor(id, false);
		JSONResponse response = new JSONResponse();
		response.setData(updateProfesor);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profesores/bloquear/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> bloquearProfesor(@PathVariable("id") Long id) throws BusinessException {
		ProfesorDTO updateProfesor = service.bloquearHabilitarProfesor(id, true);
		JSONResponse response = new JSONResponse();
		response.setData(updateProfesor);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	
	//Service
	@Resource(name = "profesorEPService")
	public void setService(ProfesorEPService profesorEPService) {
		super.service = profesorEPService;
	}

}

package ar.com.avaco.educacion.ws.controller;

import java.util.List;

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
import ar.com.avaco.educacion.ws.dto.AlumnoDTO;
import ar.com.avaco.educacion.ws.service.AlumnoEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class AlumnoRestController extends AbstractDTORestController<AlumnoDTO, Long, AlumnoEPService> {

	
	@RequestMapping(value = "/alumnos/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listAlumnos() throws Exception  {
		List<AlumnoDTO> listAlumnos = this.service.listAlumnos();
    	JSONResponse response = new JSONResponse();
		response.setData(listAlumnos);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/alumnos/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getAlumno(@PathVariable("id") Long id) throws Exception {
		AlumnoDTO alumno = this.service.getAlumno(id);
		JSONResponse response = new JSONResponse();
		response.setData(alumno);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/alumnos/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> updateAlumno(@PathVariable("id") Long id, @RequestBody AlumnoDTO alumno) throws BusinessException {
		AlumnoDTO updateAlumno = service.updateAlumno(id, alumno);
		JSONResponse response = new JSONResponse();
		response.setData(updateAlumno);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/alumnos/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody AlumnoDTO alumno) throws BusinessException {
		AlumnoDTO newAlumnoDto = service.createAlumno(alumno);
		JSONResponse response = new JSONResponse();
		response.setData(newAlumnoDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}	
		
	@RequestMapping(value = "/alumnos/habilitar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> habilitarAlumno(@PathVariable("id") Long id) throws BusinessException {
		AlumnoDTO updateAlumno = service.bloquearHabilitarAlumno(id, false);
		JSONResponse response = new JSONResponse();
		response.setData(updateAlumno);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/alumnos/bloquear/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> bloquearAlumno(@PathVariable("id") Long id) throws BusinessException {
		AlumnoDTO updateAlumno = service.bloquearHabilitarAlumno(id, true);
		JSONResponse response = new JSONResponse();
		response.setData(updateAlumno);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	//Service
	@Resource(name = "alumnoEPService")
	public void setService(AlumnoEPService alumnoEPService) {
		super.service = alumnoEPService;
	}

}

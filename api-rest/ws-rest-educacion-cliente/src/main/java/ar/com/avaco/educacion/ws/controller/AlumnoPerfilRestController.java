package ar.com.avaco.educacion.ws.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.educacion.ws.dto.AlumnoDTO;
import ar.com.avaco.educacion.ws.service.AlumnoEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class AlumnoPerfilRestController extends AbstractDTORestController<AlumnoDTO, Long, AlumnoEPService> {

	@RequestMapping(value = "/alumnos/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getAlumno(@PathVariable("id") Long id) throws Exception {
		AlumnoDTO alumno = this.service.getAlumno(id);
		JSONResponse response = new JSONResponse();
		response.setData(alumno);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	//Service
	@Resource(name = "alumnoEPService")
	public void setService(AlumnoEPService alumnoEPService) {
		super.service = alumnoEPService;
	}

}

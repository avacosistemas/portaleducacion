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
import ar.com.avaco.educacion.ws.dto.AulaAlumnoDTO;
import ar.com.avaco.educacion.ws.service.AulaAlumnoEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class AulaAlumnoRestController extends AbstractDTORestController<AulaAlumnoDTO, Long, AulaAlumnoEPService> {

	
	@RequestMapping(value = "/aulaAlumno/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listByAula(@PathVariable("id") Long idAula) throws BusinessException {
		List<AulaAlumnoDTO> listaulaAlumno = this.service.listAulaAlumno(idAula);
    	JSONResponse response = new JSONResponse();
		response.setData(listaulaAlumno);
		response.setStatus(JSONResponse.OK);
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/aulaAlumno/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addAlumno(@RequestBody AulaAlumnoDTO aulaAlumnoDTO) throws BusinessException {
		AulaAlumnoDTO aulaDtoToUpdate = service.addAlumno(aulaAlumnoDTO);
		JSONResponse response = new JSONResponse();
		response.setData(aulaDtoToUpdate);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/aulaAlumno/aula/{idAula}/alumno/{idAlumno}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> removeaulaAlumno(@PathVariable("idAula") Long idAula, @PathVariable("idAlumno") Long idAlumno) throws BusinessException {
		service.removeAulaAlumno(idAula,idAlumno);
		JSONResponse response = new JSONResponse();
		response.setData(null);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	//Service
	@Resource(name = "aulaAlumnoEPService")
	public void setService(AulaAlumnoEPService aulaAlumnoEPService) {
		super.service = aulaAlumnoEPService;
	}


}

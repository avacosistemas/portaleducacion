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
import ar.com.avaco.educacion.ws.dto.AulaProfesorDTO;
import ar.com.avaco.educacion.ws.service.AulaProfesorEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class AulaProfesorRestController extends AbstractDTORestController<AulaProfesorDTO, Long, AulaProfesorEPService> {

	
	@RequestMapping(value = "/aulaProfesor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listByAula(@PathVariable("id") Long idAula) throws BusinessException {
		List<AulaProfesorDTO> listAulaProfesor = this.service.listAulaProfesor(idAula);
    	JSONResponse response = new JSONResponse();
		response.setData(listAulaProfesor);
		response.setStatus(JSONResponse.OK);
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	//Service
	@Resource(name = "aulaProfesorEPService")
	public void setService(AulaProfesorEPService aulaProfesorEPService) {
		super.service = aulaProfesorEPService;
	}


}

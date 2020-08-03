package ar.com.avaco.educacion.ws.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.MateriaProfesorFullDTO;
import ar.com.avaco.educacion.ws.service.MateriaProfesorEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MateriaProfesorRestController extends AbstractDTORestController<MateriaProfesorFullDTO, Long, MateriaProfesorEPService> {

	@RequestMapping(value = "/materiasprofesor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listByProfesor(@PathVariable("idProfesor") Long idProfesor) throws BusinessException {
		List<MateriaProfesorFullDTO> listMateriasProfesor = this.service.listMateriasProfesor(idProfesor);
    	JSONResponse response = new JSONResponse();
		response.setData(listMateriasProfesor);
		response.setStatus(JSONResponse.OK);
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	//Service
	@Resource(name = "materiaProfesorEPService")
	public void setService(MateriaProfesorEPService materiaProfesorEPService) {
		super.service = materiaProfesorEPService;
	}

}

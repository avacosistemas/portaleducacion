package ar.com.avaco.educacion.ws.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.MateriaProfesorDTO;
import ar.com.avaco.educacion.ws.service.MateriaProfesorEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MateriaProfesorRestController extends AbstractDTORestController<MateriaProfesorDTO, Long, MateriaProfesorEPService> {


	
	@RequestMapping(value = "/materiasprofesor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listByProfesor(@PathVariable("id") Long idProfesor) throws BusinessException {
		List<MateriaProfesorDTO> listMateriasProfesor = this.service.listMateriasProfesor(idProfesor);
    	JSONResponse response = new JSONResponse();
		response.setData(listMateriasProfesor);
		response.setStatus(JSONResponse.OK);
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(path = "/materiasprofesor", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> createMateriaProfesor(@RequestParam(name="idMateria", required=true) Long idMateria, 
															  @RequestParam(name="idProfesor", required=true) Long idProfesor) throws BusinessException {
		
		MateriaProfesorDTO newMateriaProfesorDto = service.createMateriaProfesor(idMateria, idProfesor);
		JSONResponse response = new JSONResponse();
		response.setData(newMateriaProfesorDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	//Service
	@Resource(name = "materiaProfesorEPService")
	public void setService(MateriaProfesorEPService materiaProfesorEPService) {
		super.service = materiaProfesorEPService;
	}

}

package ar.com.avaco.educacion.ws.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import ar.com.avaco.educacion.ws.dto.MateriaProfesorDTO;
import ar.com.avaco.educacion.ws.dto.MateriaProfesorFullDTO;
import ar.com.avaco.educacion.ws.service.MateriaProfesorEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MateriaProfesorRestController extends AbstractDTORestController<MateriaProfesorFullDTO, Long, MateriaProfesorEPService> {

	@RequestMapping(value = "/materiasprofesor/{idProfesor}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listByProfesor(@PathVariable("idProfesor") Long idProfesor) throws BusinessException {
		List<MateriaProfesorFullDTO> listMateriasProfesor = this.service.listMateriasProfesor(idProfesor);
		listMateriasProfesor = listMateriasProfesor.stream().sorted(Comparator.comparing(MateriaProfesorFullDTO::getDescMateria)).collect(Collectors.toList() );
    	JSONResponse response = new JSONResponse();
		response.setData(listMateriasProfesor);
		response.setStatus(JSONResponse.OK);
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/materiasprofesor/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listByProfesorParam(@RequestParam("idProfesor") Long idProfesor) throws BusinessException {
		List<MateriaProfesorFullDTO> listMateriasProfesor = this.service.listMateriasProfesor(idProfesor);
    	JSONResponse response = new JSONResponse();
		response.setData(listMateriasProfesor);
		response.setStatus(JSONResponse.OK);
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/materiasprofesor/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> createMateriaProfesor(@RequestBody MateriaProfesorDTO materiaProfesor) throws BusinessException {
		
		MateriaProfesorDTO newMateriaProfesorDto = service.createMateriaProfesor(materiaProfesor);
		JSONResponse response = new JSONResponse();
		response.setData(newMateriaProfesorDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/materiasprofesor/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
		return super.delete(id);
	}
	
	@RequestMapping(path = "/materiasprofesor/id/{idProfesor}/idMateria/{idMateria}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> removeMateriaProfesor(@PathVariable("idProfesor") Long idProfesor, @PathVariable("idMateria") Long idMateria) throws BusinessException {
		service.removeMateriaProfesor(idProfesor, idMateria);
		JSONResponse response = new JSONResponse();
		response.setData(null);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	//Service
	@Resource(name = "materiaProfesorEPService")
	public void setService(MateriaProfesorEPService materiaProfesorEPService) {
		super.service = materiaProfesorEPService;
	}

}

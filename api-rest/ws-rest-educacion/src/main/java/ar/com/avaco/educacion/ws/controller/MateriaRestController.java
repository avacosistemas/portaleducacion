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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.InstitucionDTO;
import ar.com.avaco.educacion.ws.dto.MateriaDTO;
import ar.com.avaco.educacion.ws.service.MateriaEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MateriaRestController extends AbstractDTORestController<MateriaDTO, Long, MateriaEPService> {

	
	@RequestMapping(value = "/materias/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list() {
		return super.list();
	}

	@RequestMapping(value = "/materias/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@PathVariable("id") Long id) throws BusinessException {
		return super.get(id);
	}
	
	@RequestMapping(value = "/materias/nivel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listByNivel(@PathVariable("id") Integer idNivel) throws BusinessException {
		List<MateriaDTO> listMaterias = this.service.listByNivel(idNivel);
    	JSONResponse response = new JSONResponse();
		response.setData(listMaterias);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/materias/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody MateriaDTO materia) throws BusinessException {
		MateriaDTO newMateriaDto = service.createMateria(materia);
		JSONResponse response = new JSONResponse();
		response.setData(newMateriaDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/materias/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> update(@PathVariable("id") Long id, @RequestBody MateriaDTO materia) throws BusinessException {
		MateriaDTO materiaDtoToUpdate = service.updateMateria(id, materia);
		JSONResponse response = new JSONResponse();
		response.setData(materiaDtoToUpdate);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/materias/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
		return super.delete(id);
	}

	@RequestMapping(value = "/materias/", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@RequestParam Map<String, String> customQuery) throws BusinessException {
		return null;
	}
	
	//Service
	@Resource(name = "materiaEPService")
	public void setService(MateriaEPService materiaEPService) {
		super.service = materiaEPService;
	}

}

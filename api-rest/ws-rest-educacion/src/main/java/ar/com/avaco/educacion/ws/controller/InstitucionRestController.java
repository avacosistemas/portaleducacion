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
import ar.com.avaco.educacion.ws.dto.InstitucionDTO;
import ar.com.avaco.educacion.ws.service.InstitucionEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class InstitucionRestController extends AbstractDTORestController<InstitucionDTO, Long, InstitucionEPService> {

	
	@RequestMapping(value = "/instituciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list() {
		return super.list();
	}

	@RequestMapping(value = "/instituciones/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@PathVariable("id") Long id) throws BusinessException {
		return super.get(id);
	}
	

	@RequestMapping(value = "/instituciones/alumno/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listByNivel(@PathVariable("id") Long idAlumno) throws BusinessException {
		List<InstitucionDTO> listAlumnos = this.service.listByAlumno(idAlumno);
    	JSONResponse response = new JSONResponse();
		response.setData(listAlumnos);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/instituciones/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody InstitucionDTO institucion) throws BusinessException {
		InstitucionDTO newInstitucionDto = service.createInstitucion(institucion);
		JSONResponse response = new JSONResponse();
		response.setData(newInstitucionDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/instituciones/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> update(@PathVariable("id") Long id, @RequestBody InstitucionDTO institucion) throws BusinessException {
		InstitucionDTO institucionDtoToUpdate = service.updateInstitucion(id, institucion);
		JSONResponse response = new JSONResponse();
		response.setData(institucionDtoToUpdate);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/instituciones/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
		return super.delete(id);
	}

	//Service
	@Resource(name = "institucionEPService")
	public void setService(InstitucionEPService institucionEPService) {
		super.service = institucionEPService;
	}

}

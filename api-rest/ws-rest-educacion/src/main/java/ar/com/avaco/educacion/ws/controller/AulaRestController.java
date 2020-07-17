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
import ar.com.avaco.educacion.ws.dto.AulaDTO;
import ar.com.avaco.educacion.ws.service.AulaEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class AulaRestController extends AbstractDTORestController<AulaDTO, Long, AulaEPService> {

	
	@RequestMapping(value = "/aulas/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listProfesores() throws Exception  {
		List<AulaDTO> listAulas = this.service.listAulas();
    	JSONResponse response = new JSONResponse();
		response.setData(listAulas);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/aulas/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@PathVariable("id") Long id) throws BusinessException {
		AulaDTO aulaDTO=service.getAula(id);
		JSONResponse response = new JSONResponse();
		response.setData(aulaDTO);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/aulas/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody AulaDTO aula) throws BusinessException {
		AulaDTO newAulaDto = service.crearAula(aula);
		JSONResponse response = new JSONResponse();
		response.setData(newAulaDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/aulas/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
		return super.delete(id);
	}
	
	@RequestMapping(value = "/aulas/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> update(@PathVariable("id") Long id, @RequestBody AulaDTO aulaDTO) throws BusinessException {
		AulaDTO aulaDtoToUpdate = service.updateAula(id, aulaDTO);
		JSONResponse response = new JSONResponse();
		response.setData(aulaDtoToUpdate);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	
	//Service
	@Resource(name = "aulaEPService")
	public void setService(AulaEPService aulaEPService) {
		super.service = aulaEPService;
	}

}

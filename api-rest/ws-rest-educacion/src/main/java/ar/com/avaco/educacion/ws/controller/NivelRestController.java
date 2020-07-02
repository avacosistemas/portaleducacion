package ar.com.avaco.educacion.ws.controller;

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
import ar.com.avaco.educacion.ws.dto.NivelDTO;
import ar.com.avaco.educacion.ws.service.NivelEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class NivelRestController extends AbstractDTORestController<NivelDTO, Integer, NivelEPService> {

	
	@RequestMapping(value = "/niveles/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listNiveles() {
		return super.list();
	}

	@RequestMapping(value = "/niveles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@PathVariable("id") Integer id) throws BusinessException {
		return super.get(id);
	}

	@RequestMapping(value = "/niveles/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody NivelDTO nivel) throws BusinessException {
		NivelDTO newNivelDto = service.createNivel(nivel);
		JSONResponse response = new JSONResponse();
		response.setData(newNivelDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/niveles/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> update(@PathVariable("id") Integer id, @RequestBody NivelDTO nivel) throws BusinessException {
		NivelDTO nivelDtoToUpdate = service.updateNivel(id, nivel);
		JSONResponse response = new JSONResponse();
		response.setData(nivelDtoToUpdate);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/niveles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@PathVariable("id") Integer id) throws BusinessException {
		return super.delete(id);
	}

	//Service
	@Resource(name = "nivelEPService")
	public void setService(NivelEPService nivelEPService) {
		super.service = nivelEPService;
	}

}

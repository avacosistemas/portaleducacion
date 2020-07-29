package ar.com.avaco.educacion.ws.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<JSONResponse> listNiveles(@RequestParam(value= "descripcion", required= false ) String descripcion) {
		if(!StringUtils.isBlank(descripcion)) {			
			ResponseEntity<JSONResponse>resp = super.listFiltered(nivel-> nivel.getDescripcion()
					.toUpperCase().contains(descripcion.toUpperCase()));
			return resp;
		}		
		return super.list();
	}

	@RequestMapping(value = "/niveles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@PathVariable("id") Integer id) throws BusinessException {
		return super.get(id);
	}
	
	//Service
	@Resource(name = "nivelEPService")
	public void setService(NivelEPService nivelEPService) {
		super.service = nivelEPService;
	}

}

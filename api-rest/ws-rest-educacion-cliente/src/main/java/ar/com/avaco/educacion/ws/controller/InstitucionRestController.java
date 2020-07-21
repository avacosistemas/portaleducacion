package ar.com.avaco.educacion.ws.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.educacion.ws.dto.InstitucionDTO;
import ar.com.avaco.educacion.ws.service.InstitucionEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class InstitucionRestController extends AbstractDTORestController<InstitucionDTO, Long, InstitucionEPService> {
	
	@RequestMapping(value = "/instituciones/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list() {
		return super.list();
	}

	//Service
	@Resource(name = "institucionEPService")
	public void setService(InstitucionEPService institucionEPService) {
		super.service = institucionEPService;
	}

}

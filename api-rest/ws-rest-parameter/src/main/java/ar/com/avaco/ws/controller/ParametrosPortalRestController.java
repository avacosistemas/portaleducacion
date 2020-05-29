package ar.com.avaco.ws.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.service.ParametrosPortalEPService;
import ar.com.avaco.ws.rest.controller.AbstractRestBaseController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ParametrosPortalRestController extends AbstractRestBaseController {
	
	private ParametrosPortalEPService parametrosPortalEPService;
	
	@RequestMapping(value = "/parametrosPortal/tasaPrestamoInversor/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> getTasaPrestamoInversor() throws Exception {
    	return super.executeProcess("get-tasa-prestamo-inversor", Void -> { 
    		return this.parametrosPortalEPService.getTasaPrestamoInversor();
    	});
    }

	@RequestMapping(value = "/parametrosPortal/tasaPrestamoSolicitante/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getTasaPrestamoSolicitante() throws Exception {
		return super.executeProcess("get-tasa-prestamo-solicitante", Void -> { 
			return this.parametrosPortalEPService.getTasaPrestamoSolicitante();
		});
	}

	@RequestMapping(value = "/parametrosPortal/tasaFactoringInversor/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> getTasaFactoringInversor() throws Exception {
    	return super.executeProcess("get-tasa-factoring-inversor", Void -> { 
    		return this.parametrosPortalEPService.getTasaFactoringInversor();
    	});
    }

	@RequestMapping(value = "/parametrosPortal/tasaFactoringSolicitante/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getTasaFactoringSolicitante() throws Exception {
		return super.executeProcess("get-tasa-factoring-solicitante", Void -> { 
			return this.parametrosPortalEPService.getTasaFactoringSolicitante();
		});
	}

	@Resource(name = "parametrosPortalEPService")
	public void setParametrosPortalEPService(ParametrosPortalEPService parametrosPortalEPService) {
		this.parametrosPortalEPService = parametrosPortalEPService;
	}
	
}
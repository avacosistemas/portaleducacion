package ar.com.avaco.educacion.ws.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.domain.FormDef;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.service.FormDefService;
import ar.com.avaco.commons.service.ValidationFormDefService;
import ar.com.avaco.educacion.ws.dto.FaqDTO;
import ar.com.avaco.educacion.ws.service.FaqEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

/**
 * @author avaco
 */

@RestController
public class FaqRestController extends AbstractDTORestController<FaqDTO, Long, FaqEPService> {
	
	private static final String FAQ_FILTER_FIELDS_JSON = "faqFilter";
	
	private FormDefService formDefService;
	private ValidationFormDefService validationFormDefService;
	
	//-------------------Retrieve All pages--------------------------------------------------------    
    @Override
	@RequestMapping(value = "/faqs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> list() {
    	return super.list();
    }
    
	@RequestMapping(value = "/portal/faqs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> list(@RequestParam Map<String, String> customQuery) throws Exception {
		return super.executeProcess("faq", Void -> { 
			return this.service.list();
		});
    }

    //-------------------Retrieve single Pages--------------------------------------------------------  
    @RequestMapping(value = "/faqs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> get(@PathVariable("id") Long id) throws BusinessException {
    	return super.get(id);
    }
    
    //-------------------Create a Page--------------------------------------------------------    
    @RequestMapping(value = "/faqs/", method = RequestMethod.POST)
    public  ResponseEntity<JSONResponse> create(@RequestBody FaqDTO faqDTO) throws BusinessException {
    	FormDef formDef = this.formDefService.getFormDef("faqSave");
    	this.validationFormDefService.validate(faqDTO, formDef);
    	return super.create(faqDTO);
    }
    
    //------------------- Update a Page --------------------------------------------------------    
    @RequestMapping(value = "/faqs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<JSONResponse> update(@PathVariable("id") Long id, @RequestBody FaqDTO faqDTO) throws BusinessException {
    	FormDef formDef = this.formDefService.getFormDef("faqUpdate");
    	this.validationFormDefService.validate(faqDTO, formDef);
    	return super.update(id, faqDTO);
    }
    //------------------- Delete a Page --------------------------------------------------------
    
    @RequestMapping(value = "/faqs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
    	return super.delete(id);
    }

	@Resource(name = "validationFormDefService")
	public void setValidationFormDefService(ValidationFormDefService validationFormDefService) {
		this.validationFormDefService = validationFormDefService;
	}
	@Resource(name = "formDefService")
	public void setFormDefService(FormDefService formDefService) {
		this.formDefService = formDefService;
	}

	@Resource(name = "faqEPService")
	@Override
	public void setService(FaqEPService service) {
		super.service = service;
	}
	
}
package ar.com.avaco.ws.controller;

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

import ar.com.avaco.commons.domain.Faq;
import ar.com.avaco.commons.domain.FormDef;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.service.FormDefService;
import ar.com.avaco.commons.service.ValidationFormDefService;
import ar.com.avaco.filter.MapFormFilter;
import ar.com.avaco.service.FaqEPService;
import ar.com.avaco.ws.dto.FaqDTO;
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
	@RequestMapping(value = "/faqs/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> list() {
    	return super.list();
    }
    
	@RequestMapping(value = "/portal/faqs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> list(@RequestParam Map<String, String> customQuery) throws Exception {
    	return super.executeProcess("get-faqs-with-params", Void -> { 
    		return this.service.listFilter(new MapFormFilter(customQuery,this.formDefService.getFieldsDef(FAQ_FILTER_FIELDS_JSON)));
    	});
    }

	@RequestMapping(value = "/portal/faqs/legales/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listLegales() throws Exception {
		return super.executeProcess("faq-legales", Void -> { 
			return this.service.listFaqs(Faq.CATEGORY_LEGALES, Faq.SUBCATEGORY_LEGALES);
		});
	}

	@RequestMapping(value = "/portal/faqs/prestamos/inversor/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listPrestamosInversor() throws Exception {
		return super.executeProcess("faq-prestamos-inversor", Void -> { 
			return this.service.listFaqs(Faq.CATEGORY_PRESTAMOS, Faq.SUBCATEGORY_INVERSOR);
		});
	}

	@RequestMapping(value = "/portal/faqs/prestamos/solicitante/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listPrestamosSolicitante() throws Exception {
		return super.executeProcess("faq-prestamos-solicitante", Void -> { 
			return this.service.listFaqs(Faq.CATEGORY_PRESTAMOS, Faq.SUBCATEGORY_SOLICITANTE);
		});
	}

	@RequestMapping(value = "/portal/faqs/factoring/inversor/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listFactoringInversor() throws Exception {
		return super.executeProcess("faq-factoring-inversor", Void -> { 
			return this.service.listFaqs(Faq.CATEGORY_FACTORING, Faq.SUBCATEGORY_INVERSOR);
		});
	}

	@RequestMapping(value = "/portal/faqs/factoring/solicitante/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listFactoringSolicitante() throws Exception {
		return super.executeProcess("faq-factoring-solicitante", Void -> { 
			return this.service.listFaqs(Faq.CATEGORY_FACTORING, Faq.SUBCATEGORY_SOLICITANTE);
		});
	}

	@RequestMapping(value = "/portal/faqs/prestamos/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listPrestamos() throws Exception {
		return super.executeProcess("faq-prestamos", Void -> { 
			return this.service.listFaqs(Faq.CATEGORY_PRESTAMOS);
		});
	}

	@RequestMapping(value = "/portal/faqs/factoring/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listFactoring() throws Exception {
		return super.executeProcess("faq-factoring", Void -> { 
			return this.service.listFaqs(Faq.CATEGORY_FACTORING);
		});
	}

	@RequestMapping(value = "/portal/faqs/seguros/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listSeguros() throws Exception {
		return super.executeProcess("faq-seguros", Void -> { 
			return this.service.listFaqs(Faq.CATEGORY_SEGUROS);
		});
	}

	@RequestMapping(value = "/portal/faqs/mercadocapitales/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listMercadoCapitales() throws Exception {
		return super.executeProcess("faq-mecadocapitales", Void -> { 
			return this.service.listFaqs(Faq.CATEGORY_MCAPITALES);
		});
	}

    //-------------------Retrieve single Pages--------------------------------------------------------  
    @RequestMapping(value = "/faqs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> get(@PathVariable("id") Long id) throws BusinessException {
    	return super.get(id);
    }
    
    //-------------------Retrieve single Pages--------------------------------------------------------  
    @RequestMapping(value = "/faqs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> getFilterLang(@RequestParam Map<String, String> customQuery) throws BusinessException {
        return new ResponseEntity<JSONResponse>(getResponseOK(null), HttpStatus.OK);
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
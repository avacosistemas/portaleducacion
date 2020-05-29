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

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.service.I18nEPService;
import ar.com.avaco.ws.dto.I18nDTO;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

/**
 * @author avaco
 */

@RestController
public class I18nController extends AbstractDTORestController<I18nDTO, Long, I18nEPService> {
	
	//-------------------Retrieve All pages--------------------------------------------------------    
    @Override
	@RequestMapping(value = "/i18ns/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> list() {
    	return super.list();
    }
    
    //-------------------Retrieve single Pages--------------------------------------------------------  
    @RequestMapping(value = "/i18ns/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> get(@PathVariable("id") Long id) throws BusinessException {
    	return super.get(id);
    }
    
    //-------------------Retrieve single Pages--------------------------------------------------------  
    @RequestMapping(value = "/i18ns", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> getFilterLang(@RequestParam Map<String, String> customQuery) throws BusinessException {
        return new ResponseEntity<JSONResponse>(getResponseOK(null), HttpStatus.OK);
    }
       
    //-------------------Create a Page--------------------------------------------------------    
    @RequestMapping(value = "/i18ns/", method = RequestMethod.POST)
    public  ResponseEntity<JSONResponse> create(@RequestBody I18nDTO i18nDTO) throws BusinessException {
    	return super.create(i18nDTO);
    }
    
    //------------------- Update a Page --------------------------------------------------------    
    @RequestMapping(value = "/i18ns/{id}", method = RequestMethod.PUT)
    public ResponseEntity<JSONResponse> update(@PathVariable("id") Long id, @RequestBody I18nDTO i18nDTO) throws BusinessException {
    	return super.update(id, i18nDTO);
    }
    //------------------- Delete a Page --------------------------------------------------------
    
    @RequestMapping(value = "/i18ns/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
    	return super.delete(id);
    }
    	
	@Resource(name = "i18nEPService")
	public void setService(I18nEPService i18nEPService) {
		super.service = i18nEPService;
	}
	
}
package ar.com.avaco.ws.controller;

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

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.service.ParameterEPService;
import ar.com.avaco.ws.dto.ParameterDTO;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

/**
 * @author avaco
 */

@RestController
public class ParameterController extends AbstractDTORestController<ParameterDTO, Integer, ParameterEPService> {
	
	
	//-------------------Retrieve All pages--------------------------------------------------------    
	@RequestMapping(value = "/parameters/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> list() {
    	return super.list();
    }
    
	//-------------------Retrieve single Pages--------------------------------------------------------  
    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> get(@PathVariable("id") Integer id) throws BusinessException {
    	return super.get(id);
    }
       
    //-------------------Create a Parameter--------------------------------------------------------    
    @RequestMapping(value = "/parameters/", method = RequestMethod.POST)
    public  ResponseEntity<JSONResponse> create(@RequestBody ParameterDTO parameter) throws BusinessException {
    	return super.create(parameter);
    }
    
    //------------------- Update a Parameter --------------------------------------------------------    
    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.PUT)
    public ResponseEntity<JSONResponse> update(@PathVariable("id") Integer id, @RequestBody ParameterDTO parameter) throws BusinessException {
    	return super.update(id, parameter);
    }
    
    //------------------- Delete a Parameter --------------------------------------------------------    
    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONResponse> delete(@PathVariable("id") Integer id) throws BusinessException {
    	return super.delete(id);
    }

    //------------------- Delete a Parameter --------------------------------------------------------    
    @RequestMapping(value = "/parameters", method = RequestMethod.DELETE)
    public ResponseEntity<JSONResponse> delete(@RequestParam Map<String, String> customQuery) throws BusinessException {
    	return super.deleteAll(customQuery);
    }

	@Resource(name = "parameterEPService")
	public void setService(ParameterEPService parameterEPService) {
		super.service = parameterEPService;
	}
	
}
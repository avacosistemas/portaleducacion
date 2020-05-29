package ar.com.avaco.ws.controller;

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
import ar.com.avaco.service.WordEPService;
import ar.com.avaco.ws.dto.WordDTO;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

/**
 * @author avaco
 */

@RestController
public class WordController extends AbstractDTORestController<WordDTO, Long, WordEPService> {
	
	
	//-------------------Retrieve All pages--------------------------------------------------------    
	@RequestMapping(value = "/i18ns/words/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> list() {
    	return super.list();
    }
    //-------------------Retrieve single Pages--------------------------------------------------------  
    @RequestMapping(value = "/i18ns/words/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> get(@PathVariable("id") Long id) throws BusinessException {
    	return super.get(id);
    }

    @RequestMapping(value = "/i18ns/public/words/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> getByKey(@PathVariable("key") String key) throws BusinessException {
    	HttpStatus httpStatus = HttpStatus.OK;
    	WordDTO entity = this.service.getByKey(key);
    	JSONResponse response = null;
    	if(entity == null) {
    		response = new JSONResponse();
    		response.setStatus(JSONResponse.ERROR);
    		response.setData(entity);
    		httpStatus = HttpStatus.NOT_FOUND;
    	}else {
    		response = getResponseOK(entity);
    	}
        return new ResponseEntity<JSONResponse>(response, httpStatus);
    }
       
    //-------------------Create a Dictionary--------------------------------------------------------    
    @RequestMapping(value = "/i18ns/words/", method = RequestMethod.POST)
    public  ResponseEntity<JSONResponse> create(@RequestBody WordDTO wordDTO) throws BusinessException {
    	return super.create(wordDTO);
    }
    
    //------------------- Update a Dictionary --------------------------------------------------------    
    @RequestMapping(value = "/i18ns/words/{id}", method = RequestMethod.PUT)
    public ResponseEntity<JSONResponse> update(@PathVariable("id") Long id, @RequestBody WordDTO wordDTO) throws BusinessException {
    	return super.update(id, wordDTO);
    }
    //------------------- Delete a Dictionary --------------------------------------------------------
    
    @RequestMapping(value = "/i18ns/words/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
    	return super.delete(id);
    }
	
	@Resource(name = "wordEPService")
	public void setService(WordEPService wordEPService) {
		super.service = wordEPService;
	}
	
}
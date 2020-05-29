package ar.com.avaco.ws.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.service.ContactUSEPService;
import ar.com.avaco.ws.dto.ContactUSDTO;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

/**
 * @author avaco
 */

@RestController
public class ContactUSController extends AbstractDTORestController<ContactUSDTO, Long, ContactUSEPService> {
	
	//-------------------Retrieve All pages--------------------------------------------------------    
    @Override
	@RequestMapping(value = "/contact-us/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> list() {
    	return super.list();
    }
    
    //-------------------Create a Page--------------------------------------------------------    
    @RequestMapping(value = "/contact-us/send/", method = RequestMethod.POST)
    public  ResponseEntity<JSONResponse> send(@RequestBody ContactUSDTO contactUSDTO) throws Exception {
    	return super.executeProcess("send-email", Void -> {return service.send(contactUSDTO);});
    }
    
    //------------------- Delete a Page --------------------------------------------------------
    
    @RequestMapping(value = "/contact-us/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
    	return super.delete(id);
    }
    	
	@Resource(name = "contactUSEPService")
	public void setService(ContactUSEPService contactUSEPService) {
		super.service = contactUSEPService;
	}
	
}
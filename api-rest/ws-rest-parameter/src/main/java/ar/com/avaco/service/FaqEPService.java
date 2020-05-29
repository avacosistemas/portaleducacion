/**
 * 
 */
package ar.com.avaco.service;

import java.util.List;

import ar.com.avaco.ws.dto.FaqDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface FaqEPService  extends CRUDEPService<Long, FaqDTO> {

	public List<FaqDTO> listFaqs(String category, String subcategory);

	public List<FaqDTO> listFaqs(String category);
	
}

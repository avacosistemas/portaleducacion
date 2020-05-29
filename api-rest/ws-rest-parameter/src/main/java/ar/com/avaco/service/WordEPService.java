/**
 * 
 */
package ar.com.avaco.service;

import ar.com.avaco.ws.dto.WordDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

/**
 * 
 *
 */
public interface WordEPService extends CRUDEPService<Long, WordDTO>{

	WordDTO getByKey(String key);
	
}

/**
 * 
 */
package ar.com.avaco.service;

import ar.com.avaco.ws.dto.I18nDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;
import ar.com.avaco.ws.rest.service.exception.EntityNotFoundException;

/**
 * 
 *
 */
public interface I18nEPService extends CRUDEPService<Long, I18nDTO> {

	public I18nDTO getByNameAndLang(String name, String lang) throws EntityNotFoundException;

}

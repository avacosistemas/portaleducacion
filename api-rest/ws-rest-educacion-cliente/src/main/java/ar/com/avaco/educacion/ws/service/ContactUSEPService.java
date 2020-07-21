/**
 * 
 */
package ar.com.avaco.educacion.ws.service;

import ar.com.avaco.educacion.ws.dto.ContactUSDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface ContactUSEPService  extends CRUDEPService<Long, ContactUSDTO> {
	public ContactUSDTO send(ContactUSDTO dto);
}

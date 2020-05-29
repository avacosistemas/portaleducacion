/**
 * 
 */
package ar.com.avaco.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.Resource
;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ar.com.avaco.commons.domain.ContactUS;
import ar.com.avaco.commons.service.ContactUSService;
import ar.com.avaco.service.ContactUSEPService;
import ar.com.avaco.ws.dto.ContactUSDTO;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

/**
 * @author avaco
 */

@Service("contactUSEPService")
public class ContactUSEPServiceImpl extends CRUDEPBaseService<Long, ContactUSDTO, ContactUS, ContactUSService> implements ContactUSEPService {
	
	@Value("${prop.dateHourPattern}")
	private String dateHourPattern;
	
	@Override
	public ContactUS convertToEntity(ContactUSDTO dto) {
		ContactUS entity = new ContactUS();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setMessage(dto.getMessage());
		entity.setTelephone(dto.getTelephone());
		return entity;
	}

	@Override
	public ContactUSDTO convertToDto(ContactUS entity) {
		ContactUSDTO dto = new ContactUSDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		dto.setMessage(entity.getMessage());
		dto.setTelephone(entity.getTelephone());
		SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getInstance();
		sdf.applyPattern(this.dateHourPattern);
		dto.setDate(sdf.format(entity.getDate()));
		return dto;
	}

	public ContactUSDTO send(ContactUSDTO dto) {
		return convertToDto(service.send(convertToEntity(dto)));
	}
	
	@Override
	@Resource(name = "contactUSService")
	protected void setService(ContactUSService contactUSService) {
		this.service = contactUSService;
	}


	
}

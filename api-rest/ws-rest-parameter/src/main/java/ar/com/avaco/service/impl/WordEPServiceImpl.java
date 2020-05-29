/**
 * 
 */
package ar.com.avaco.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import ar.com.avaco.commons.domain.Word;
import ar.com.avaco.commons.service.WordService;
import ar.com.avaco.service.WordEPService;
import ar.com.avaco.ws.dto.WordDTO;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

/**
 * @author avaco
 */

@Service("wordEPService")
public class WordEPServiceImpl extends CRUDEPBaseService<Long, WordDTO, Word, WordService> implements WordEPService {
	
	@Override
	@Resource(name = "wordService")
	public void setService(WordService wordService) {
		this.service = wordService;
	}

	@Override
	public WordDTO getByKey(String key) {
		Word byKey = service.getByKey(key);
		return convertToDto(byKey);
	}
	
	@Override
	public Word convertToEntity(WordDTO dto) {
		Word entity = new Word();
		entity.setId(dto.getId());
		entity.setKey(dto.getKey());
		entity.setValue(dto.getValue());
		return entity;
	}

	@Override
	public WordDTO convertToDto(Word entity) {
		WordDTO dto = new WordDTO();
		dto.setId(entity.getId());
		dto.setKey(entity.getKey());
		dto.setValue(entity.getValue());
		return dto;
	}
}

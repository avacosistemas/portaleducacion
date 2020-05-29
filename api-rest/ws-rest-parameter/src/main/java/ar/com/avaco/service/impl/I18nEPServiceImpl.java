/**
 * 
 */
package ar.com.avaco.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import ar.com.avaco.commons.domain.I18n;
import ar.com.avaco.commons.domain.Word;
import ar.com.avaco.commons.service.I18nService;
import ar.com.avaco.service.I18nEPService;
import ar.com.avaco.ws.dto.I18nDTO;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;
import ar.com.avaco.ws.rest.service.exception.EntityNotFoundException;

/**
 * @author avaco
 */

@Service("i18nEPService")
public class I18nEPServiceImpl extends CRUDEPBaseService<Long, I18nDTO, I18n, I18nService> implements I18nEPService {

	@Override
	public I18nDTO getByNameAndLang(String name, String lang) throws EntityNotFoundException {
		I18nDTO dto = new I18nDTO();
		I18n i18n = this.service.getByNameAndLang(name, lang);
		if(i18n == null) {
			throw new EntityNotFoundException("I18n with name " + name + " and lang " + lang + " not found");
		}
		dto.setId(i18n.getId());
		dto.setDescription(i18n.getDescription());
		Set<Word> words = new HashSet<>();
		i18n.getWords().stream().forEach(e -> {
			Word w = new Word();
			w.setId(e.getId());
			w.setKey(e.getKey());
			w.setValue(e.getValue());
		});
		dto.setWords(words);
		return dto;
	}


	@Override
	@Resource(name = "i18nService")
	public void setService(I18nService i18nService) {
		this.service = i18nService;
	}

	@Override
	protected I18n convertToEntity(I18nDTO dto) {
		I18n entity = new I18n();
		entity.setDescription(dto.getDescription());
		Set<Word> words = new HashSet<>();
		dto.getWords().stream().forEach(e -> {
			Word w = new Word();
			w.setId(e.getId());
			w.setKey(e.getKey());
			w.setValue(e.getValue());
			w.setI18n(entity);
		});
		entity.setWords(words);
		return entity;
	}

	@Override
	protected I18nDTO convertToDto(I18n entity) {
		I18nDTO dto = new I18nDTO();
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());
		Set<Word> words = new HashSet<>();
		entity.getWords().stream().forEach(e -> {
			Word w = new Word();
			w.setId(e.getId());
			w.setKey(e.getKey());
			w.setValue(e.getValue());
		});
		dto.setWords(words);
		return dto;
	}
	
}

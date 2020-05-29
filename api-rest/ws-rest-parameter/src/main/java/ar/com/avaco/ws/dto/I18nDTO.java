package ar.com.avaco.ws.dto;

import java.util.HashSet;
import java.util.Set;

import ar.com.avaco.commons.domain.Word;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class I18nDTO extends DTOEntity<Long> {

	private Long id;

	private String lang;

	private String description;

	private Set<Word> words = new HashSet<Word>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Word> getWords() {
		return words;
	}

	public void setWords(Set<Word> words) {
		this.words = words;
	}

}
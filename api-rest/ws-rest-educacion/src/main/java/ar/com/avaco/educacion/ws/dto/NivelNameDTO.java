package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Nivel;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class NivelNameDTO extends DTOEntity<Integer> {
	
	private Integer id;
	private String name;	
	
	public NivelNameDTO() {}	

	public NivelNameDTO(Nivel nivel) {
		this.setDTO(nivel);
	}

	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDTO(Nivel nivel) {
		this.setId(nivel.getId());
		this.setName(nivel.getDescripcion());
	}
	

}

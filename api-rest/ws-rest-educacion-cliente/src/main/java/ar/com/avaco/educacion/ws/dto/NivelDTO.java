package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Nivel;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class NivelDTO extends DTOEntity<Integer> {
	
	private Integer id;
	private String descripcion;	
	
	public NivelDTO() {}

	public NivelDTO(Nivel nivel) {
		this.setDTO(nivel);
	}

	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	//Converter
	public Nivel toEntity() {
		Nivel nivel = new Nivel();
		nivel.setId(this.getId());
		nivel.setDescripcion(this.getDescripcion());
	
		return nivel;
	}

	public void setDTO(Nivel nivel) {
		this.setId(nivel.getId());
		this.setDescripcion(nivel.getDescripcion());
	}
	

}

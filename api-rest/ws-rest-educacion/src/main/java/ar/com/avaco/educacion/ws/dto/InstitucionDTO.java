package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Institucion;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class InstitucionDTO extends DTOEntity<Long> {
	
	private Long id;
	private String nombre;
	
	public InstitucionDTO() {}

	public InstitucionDTO(Institucion institucion) {
		this.setDTO(institucion);
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//Converter
	public Institucion toEntity() {
		Institucion institucion = new Institucion();
		institucion.setId(this.getId());
		institucion.setNombre(this.getNombre());
	
		return institucion;
	}

	public void setDTO(Institucion institucion) {
		this.setId(institucion.getId());
		this.setNombre(institucion.getNombre());
	}
	

}

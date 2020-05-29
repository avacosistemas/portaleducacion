package ar.com.avaco.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class DestinoDTO extends DTOEntity<Long> {

	private Long id;

	private String descripcion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

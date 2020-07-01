package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Nivel;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class MateriaDTO extends DTOEntity<Long> {
	
	private Long id;
	private String descripcion;
	private Integer idNivel;
	
	public MateriaDTO() {}

	public MateriaDTO(Materia materia) {
		this.setDTO(materia);
	}

	//Getters and Setters
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
	
	public Integer getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}

	//Converter
	public Materia toEntity() {
		Materia materia = new Materia();
		materia.setId(this.getId());
		materia.setDescripcion(this.getDescripcion());
		
		Nivel nivel = new Nivel();
		nivel.setId(this.getIdNivel());

		materia.setNivel(nivel);
		
		return materia;
	}

	public void setDTO(Materia materia) {
		this.setId(materia.getId());
		this.setDescripcion(materia.getDescripcion());	
		this.setIdNivel(materia.getNivel().getId());
		
	}
	

}

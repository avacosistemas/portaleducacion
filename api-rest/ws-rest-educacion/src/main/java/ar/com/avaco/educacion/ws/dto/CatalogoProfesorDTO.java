package ar.com.avaco.educacion.ws.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class CatalogoProfesorDTO extends DTOEntity<Long> {
	
	private Long id;
	private String nombreApellido;
	private byte[] foto;
	private Double valorHora;	
	private List<MateriaDTO> materias;
	
	public CatalogoProfesorDTO() {}

	public CatalogoProfesorDTO(Profesor profesor) {
		this.setDTO(profesor);
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}

	public List<MateriaDTO> getMaterias() {
		return materias;
	}

	public void setMaterias(List<MateriaDTO> materias) {
		this.materias = materias;
	}

	public void setDTO(Profesor profesor) {
		this.setId(profesor.getId());
		this.setNombreApellido(profesor.getNombreApellido());
		
		this.setFoto(profesor.getFoto());
		this.setValorHora(profesor.getValorHora());
		
		List<MateriaDTO> materias = new ArrayList<>();
		MateriaDTO materia = new MateriaDTO();
		
		for(Materia m: profesor.getMaterias()) {
			materia = new MateriaDTO(m);
			materias.add(materia);
		}
		
		this.setMaterias(materias);
	}
	

}

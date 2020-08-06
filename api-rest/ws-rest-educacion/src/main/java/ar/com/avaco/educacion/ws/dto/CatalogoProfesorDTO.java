package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class CatalogoProfesorDTO extends DTOEntity<Long> {

	private Long id;
	private String nombreApellido;
	private String descripcion;

	private byte[] foto;
	private Double valorHora;

	private String materias;

	private Double calificacion;

	public CatalogoProfesorDTO() {
	}

	public CatalogoProfesorDTO(Profesor profesor) {
		this.setDTO(profesor);
	}

	// Getters and Setters
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

	public void setDTO(Profesor profesor) {
		this.setId(profesor.getId());
		this.setNombreApellido(profesor.getNombreApellido());

		this.setFoto(profesor.getFoto());
		this.setValorHora(profesor.getValorHora());

		this.setMaterias(profesor.getMateriasString());
		this.setDescripcion(profesor.getDescripcion());
		this.setCalificacion(profesor.getCalificacion());
	}

	public String getMaterias() {
		return materias;
	}

	public void setMaterias(String materias) {
		this.materias = materias;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}

}

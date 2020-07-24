package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class CompraAulaDTO extends DTOEntity<Long> {

	protected Long id;

	protected Long idProfesor;

	protected Long idMateria;

	protected String dia;

	protected String hora;

	public CompraAulaDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}

	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

}

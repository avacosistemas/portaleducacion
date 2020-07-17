package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class AulaProfesorDTO extends DTOEntity<Long> {

	protected Long idAula;
	protected Long idProfesor;

	public Long getIdAula() {
		return idAula;
	}

	public void setIdAula(Long idAula) {
		this.idAula = idAula;
	}

	public Long getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}

	@Override
	public void setId(Long id) {
				
	}

	@Override
	public Long getId() {
		return null;
	}

}

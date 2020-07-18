package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class AulaAlumnoDTO extends DTOEntity<Long> {

	protected Long idAula;
	protected Long idAlumno;

	public Long getIdAula() {
		return idAula;
	}

	public void setIdAula(Long idAula) {
		this.idAula = idAula;
	}

	public Long getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Long idAlumno) {
		this.idAlumno = idAlumno;
	}

	@Override
	public void setId(Long id) {

	}

	@Override
	public Long getId() {
		return null;
	}

}

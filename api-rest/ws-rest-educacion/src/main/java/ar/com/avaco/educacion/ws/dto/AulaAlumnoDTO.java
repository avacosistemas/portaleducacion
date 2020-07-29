package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class AulaAlumnoDTO extends DTOEntity<Long> {

	private Long id;
	private String idAula;
	private String idAlumno;

	private String nombreAlumno;
	private String calificacion;
	private String comentario;

	public String getIdAula() {
		return idAula;
	}

	public void setIdAula(String idAula) {
		this.idAula = idAula;
	}

	public String getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(String idAlumno) {
		this.idAlumno = idAlumno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}

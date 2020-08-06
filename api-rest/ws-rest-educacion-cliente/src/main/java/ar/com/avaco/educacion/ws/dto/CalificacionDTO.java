package ar.com.avaco.educacion.ws.dto;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.educacion.domain.entities.AulaAlumno;
import ar.com.avaco.utils.DateUtils;

public class CalificacionDTO {

	private String alumno;

	private Long calificacion;

	private String comentarios;

	private String materia;

	private String fechaHora;

	public CalificacionDTO(AulaAlumno aa) {
		this.alumno = aa.getAlumno().getNombreApellido();
		this.calificacion = aa.getCalificacion();
		this.comentarios = aa.getComentario();
		this.materia = aa.getAula().getMateria().getDescripcion();
		this.fechaHora = DateUtils.toString(aa.getAula().getDia()) + " - "
				+ StringUtils.leftPad("0", 2, aa.getAula().getHora().toString()) + ":00 Hs.";
	}

	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	public Long getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

}

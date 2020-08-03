package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PreguntaRespuestaDTO extends DTOEntity<Long> {
	
	private Long id;
	private Long idProfesor;
	private String nombreAlumno;
	private String pregunta;
	private String respuesta;
	private String fechaPregunta;
	private String fechaRespuesta;

	public PreguntaRespuestaDTO() {
	}

	public PreguntaRespuestaDTO(PreguntaRespuesta preguntaRta) {
		this.setDTO(preguntaRta);
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

	public String getFechaPregunta() {
		return fechaPregunta;
	}

	public void setFechaPregunta(String fechaPregunta) {
		this.fechaPregunta = fechaPregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public void setDTO(PreguntaRespuesta preguntaRta) {
		this.setId(preguntaRta.getId());
		this.setIdProfesor(preguntaRta.getProfesor().getId());
		this.setPregunta(preguntaRta.getPregunta());
		this.setRespuesta(preguntaRta.getRespuesta());
		this.setFechaPregunta(DateUtils.toString(preguntaRta.getFechaPregunta(), DateUtils.FULL_24_HS));
		if (preguntaRta.getFechaRespuesta() != null) {
			this.setFechaRespuesta(DateUtils.toString(preguntaRta.getFechaRespuesta(), DateUtils.FULL_24_HS));
		}
		this.setNombreAlumno(preguntaRta.getAlumno().getNombreApellido());
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public String getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(String fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

}

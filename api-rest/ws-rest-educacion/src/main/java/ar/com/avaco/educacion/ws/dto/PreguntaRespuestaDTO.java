package ar.com.avaco.educacion.ws.dto;

import java.util.Date;

import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PreguntaRespuestaDTO extends DTOEntity<Long> {
	
	private Long id;
	private Long idProfesor;
	private String pregunta;
	private String respuesta;
	private Date fechaPregunta;
	
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

	public Date getFechaPregunta() {
		return fechaPregunta;
	}

	public void setFechaPregunta(Date fechaPregunta) {
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

	public PreguntaRespuesta toEntity() {
		Profesor profesor = new Profesor();
		PreguntaRespuesta preguntaRta = new PreguntaRespuesta();
		
		preguntaRta.setId(this.getId());
		profesor.setId(this.getIdProfesor());
		
		preguntaRta.setProfesor(profesor);
		
		preguntaRta.setPregunta(this.getPregunta());
		
		return preguntaRta;
	}

	public void setDTO(PreguntaRespuesta preguntaRta) {
		this.setId(preguntaRta.getId());
		this.setIdProfesor(preguntaRta.getProfesor().getId());
		this.setPregunta(preguntaRta.getPregunta());
		this.setRespuesta(preguntaRta.getRespuesta());
		this.setFechaPregunta(preguntaRta.getFechaPregunta());
	
	}

}

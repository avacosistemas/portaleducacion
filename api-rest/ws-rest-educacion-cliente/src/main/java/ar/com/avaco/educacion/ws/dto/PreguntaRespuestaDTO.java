package ar.com.avaco.educacion.ws.dto;

import java.time.LocalDate;

import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class PreguntaRespuestaDTO extends DTOEntity<Long> {
	
	private Long id;
	private Long idProfesor;
	private String pregunta;
	private String respuesta;
	private String fecha;
	private String hora;
	
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
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
		this.setFecha(DateUtils.toString(preguntaRta.getFechaPregunta()));
		//this.setHora(preguntaRta.getHoraPregunta()!=null ? preguntaRta.getHoraPregunta().toString():"");
		
	}

}

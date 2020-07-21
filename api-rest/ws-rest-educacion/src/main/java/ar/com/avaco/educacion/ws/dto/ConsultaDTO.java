package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ConsultaDTO extends DTOEntity<Long> {
	
	private Long id;
	private Long idProfesor;
	private String consulta;	
	
	public ConsultaDTO() {}

	public ConsultaDTO(PreguntaRespuesta preguntaRta) {
		this.setDTO(preguntaRta);
	}

	//Getters and Setters
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

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	//Converter
	public PreguntaRespuesta toEntity() {
		Profesor profesor = new Profesor();
		
		PreguntaRespuesta preguntaRta = new PreguntaRespuesta();
		preguntaRta.setId(this.getId());
		
		profesor.setId(this.getIdProfesor());
		preguntaRta.setProfesor(profesor);
		preguntaRta.setPregunta(this.consulta);
		
		return preguntaRta;
	}

	public void setDTO(PreguntaRespuesta preguntaRta) {
		this.setId(preguntaRta.getId());
		this.setIdProfesor(preguntaRta.getProfesor().getId());	
		this.setConsulta(preguntaRta.getPregunta());
	}
	

}

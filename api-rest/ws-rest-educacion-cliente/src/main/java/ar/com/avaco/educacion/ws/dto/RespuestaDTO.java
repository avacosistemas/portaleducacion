package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class RespuestaDTO extends DTOEntity<Long> {

	private Long id;

	private String respuesta;

	private Long idPreguntaRespuesta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Long getIdPreguntaRespuesta() {
		return idPreguntaRespuesta;
	}

	public void setIdPreguntaRespuesta(Long idPreguntaRespuesta) {
		this.idPreguntaRespuesta = idPreguntaRespuesta;
	}

}

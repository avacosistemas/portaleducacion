package ar.com.avaco.educacion.ws.dto;

public class PreguntaDTO {

	private String pregunta;

	private Long IdProfesor;

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Long getIdProfesor() {
		return IdProfesor;
	}

	public void setIdProfesor(Long idProfesor) {
		IdProfesor = idProfesor;
	}

}

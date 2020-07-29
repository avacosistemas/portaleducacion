package ar.com.avaco.educacion.ws.dto;

public class AlumnoDTO extends ClienteDTO {

	private Long idInstitucion;
	private String nombreInstitucion;

	private String nombreApellido;

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public Long getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(Long idInstitucion) {
		this.idInstitucion = idInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

}

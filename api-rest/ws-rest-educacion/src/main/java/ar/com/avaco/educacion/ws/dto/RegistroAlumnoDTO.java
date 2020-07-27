package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Alumno;

public class RegistroAlumnoDTO extends AlumnoDTO {

	protected String password;
	
	protected String secondPassword;
	
	public RegistroAlumnoDTO() {}

	public RegistroAlumnoDTO(Alumno alumno) {
		this.setDTO(alumno);
		this.setPassword(alumno.getPassword());
	}
	
	public Alumno toEntity() {
		Alumno alumno=super.toEntity();
		alumno.setPassword(this.getPassword());
		return alumno;
	}

	public void setDTO(Alumno alumno) {
		super.setDTO(alumno);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecondPassword() {
		return secondPassword;
	}

	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}
	
	

}

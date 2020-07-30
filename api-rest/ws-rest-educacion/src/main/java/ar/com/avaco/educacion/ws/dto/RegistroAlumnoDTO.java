package ar.com.avaco.educacion.ws.dto;

public class RegistroAlumnoDTO extends AlumnoDTO {

	protected String password;
	
	protected String secondPassword;
	
	public RegistroAlumnoDTO() {}

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

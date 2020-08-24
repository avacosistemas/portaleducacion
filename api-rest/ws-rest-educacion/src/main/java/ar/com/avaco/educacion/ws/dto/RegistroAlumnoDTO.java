package ar.com.avaco.educacion.ws.dto;

public class RegistroAlumnoDTO extends AlumnoDTO {

	protected String password;
	
	protected String secondPassword;
	
	private Boolean checkTerminosCondiciones;
	
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

	public Boolean getCheckTerminosCondiciones() {
		return checkTerminosCondiciones;
	}

	public void setCheckTerminosCondiciones(Boolean checkTerminosCondiciones) {
		this.checkTerminosCondiciones = checkTerminosCondiciones;
	}
	
	
	

}

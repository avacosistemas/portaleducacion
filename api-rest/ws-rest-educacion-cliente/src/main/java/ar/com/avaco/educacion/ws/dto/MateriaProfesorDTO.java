package ar.com.avaco.educacion.ws.dto;

public class MateriaProfesorDTO {
	
	private Long idProfesor;
	private Long idMateria;
	
	public MateriaProfesorDTO() {}

	public Long getIdProfesor() {
		return idProfesor;
	}

	//Getters and Setters
	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}

	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

}

package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.HorasAlumno;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class HorasAlumnoDTO extends DTOEntity<Long> {

	protected Long id;

	protected Long idProfesor;
	
	protected Long idAlumno;
	
	protected Integer horas;

	public HorasAlumnoDTO() {

	}

	public HorasAlumnoDTO(HorasAlumno horasAlumno) {
		setDTO(horasAlumno);
	}

	public HorasAlumno toEntity() {

		HorasAlumno horasAlumno= new HorasAlumno();
		
		horasAlumno.setId(this.getId());
		
		Alumno alumno = new Alumno();
		alumno.setId(idAlumno);
		horasAlumno.setAlumno(alumno);
		
		Profesor profesor=new Profesor();
		profesor.setId(idProfesor);
		
		horasAlumno.setHorasDisponibles(horas);
		
		return horasAlumno;
	}

	public void setDTO(HorasAlumno horasAlumno) {
		this.setId(horasAlumno.getId());
		this.setIdAlumno(horasAlumno.getAlumno()!=null?horasAlumno.getAlumno().getId():null);
		this.setIdProfesor(horasAlumno.getProfesor()!=null?horasAlumno.getProfesor().getId():null);
		this.setHoras(horasAlumno.getHorasDisponibles());
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

	public Long getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Long idAlumno) {
		this.idAlumno = idAlumno;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}
	
	

		
	
}

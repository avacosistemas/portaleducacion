package ar.com.avaco.educacion.ws.dto;

import java.text.ParseException;

import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.Institucion;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class AulaDTO extends DTOEntity<Long> {

	protected Long id;

	protected Long idMateria;

	protected Long idInstitucion;

	protected String dia;

	protected String hora;

	protected String idProfesor;

	public AulaDTO() {

	}

	public AulaDTO(Aula aula) {
		setDTO(aula);
	}

	public Aula toEntity() {
		Aula aula = new Aula();
		aula.setId(this.getId());
		try {
			aula.setDia(DateUtils.toDate(getDia(), "dd/MM/yyyy"));
		} catch (ParseException e) {
			// Nada no deberia llegar aca
		}
		aula.setHora(Integer.parseInt(getHora()));

		Materia materia = new Materia();
		materia.setId(idMateria);
		aula.setMateria(materia);

		Profesor profesor = new Profesor();
		profesor.setId(Long.parseLong(this.getIdProfesor()));

		if (this.getIdInstitucion() != null) {
			Institucion institucion = new Institucion();
			institucion.setId(this.getIdInstitucion());
			aula.setInstitucion(institucion);
		}

//		if (alumnos!=null && !alumnos.isEmpty()) {
//			HashSet<Alumno> alumnosList=new HashSet<>();
//			for (AlumnoDTO alumnoDTO : alumnos) {
//				Alumno alumno=alumnoDTO.toEntity();
//				alumnosList.add(alumno);
//			}
//			aula.setAlumnos(alumnosList);
//		}

//		if (profesores!=null && !profesores.isEmpty()) {
//			HashSet<Profesor> profesoresList=new HashSet<>();
//			for (ProfesorPerfilDTO profesorDTO : profesores) {
//				Profesor profesor=profesorDTO.toEntity();
//				profesoresList.add(profesor);
//			}
//			aula.setProfesores(profesoresList);
//		}

		return aula;
	}

	public void setDTO(Aula aula) {
		this.setId(aula.getId());

//		if (aula.getAlumnos() != null && !aula.getAlumnos().isEmpty()) {
//			HashSet<AlumnoDTO> alumnoDTOs = new HashSet<>();
//			for (Alumno alumno : aula.getAlumnos()) {
//				alumnoDTOs.add(new AlumnoDTO(alumno));
//			}
//			this.setAlumnos(alumnoDTOs);
//		}

//		if (aula.getProfesores() != null && !aula.getProfesores().isEmpty()) {
//			HashSet<ProfesorPerfilDTO> profesoresDTOs = new HashSet<>();
//			for (Profesor profesor : aula.getProfesores()) {
//				profesoresDTOs.add(new ProfesorPerfilDTO(profesor));
//			}
//			this.setProfesores(profesoresDTOs);
//		}

		// this.setComentarios(aula.getComentarios());

		this.setIdMateria(aula.getMateria().getId());
		this.setIdInstitucion(aula.getInstitucion() != null ? aula.getInstitucion().getId() : null);
		this.setDia(DateUtils.toString(aula.getDia()));
		this.setHora(aula.getHora().toString());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Long getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(Long idInstitucion) {
		this.idInstitucion = idInstitucion;
	}

	public String getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}

}

package ar.com.avaco.educacion.ws.dto;


import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.Comentario;
import ar.com.avaco.educacion.domain.entities.Institucion;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class AulaDTO extends DTOEntity<Long> {

	protected Long id;

	protected Set<AlumnoDTO> alumnos;

	protected Set<ProfesorDTO> profesores;

	protected Set<Comentario> comentarios = new HashSet<>();

	protected Long idMateria;
	
	protected Long idInstitucion;

	protected String dia;
	protected String hora;

	public AulaDTO() {

	}

	public AulaDTO(Aula aula) {
		setDTO(aula);
	}

	public Aula toEntity() {
	
		Aula aula = new Aula();
		aula.setId(this.getId());
		try {
			aula.setDia(DateUtils.toDate(getDia()));
		} catch (ParseException e) {
			e.printStackTrace();
			aula.setDia(null);
		}
		aula.setHora(getHora());

		Materia materia = new Materia();
		materia.setId(idMateria);
		aula.setMateria(materia);
		
		if (this.getIdInstitucion()!=null) {
			Institucion institucion=new Institucion();
			institucion.setId(this.getIdInstitucion());
			aula.setInstitucion(institucion);
		}

		if (alumnos!=null && !alumnos.isEmpty()) {
			HashSet<Alumno> alumnosList=new HashSet<>();
			for (AlumnoDTO alumnoDTO : alumnos) {
				Alumno alumno=alumnoDTO.toEntity();
				alumnosList.add(alumno);
			}
			aula.setAlumnos(alumnosList);
		}
		
		if (profesores!=null && !profesores.isEmpty()) {
			HashSet<Profesor> profesoresList=new HashSet<>();
			for (ProfesorDTO profesorDTO : profesores) {
				Profesor profesor=profesorDTO.toEntity();
				profesoresList.add(profesor);
			}
			aula.setProfesores(profesoresList);
		}
		
		//aula.setComentarios(getComentarios());

		return aula;
	}

	public void setDTO(Aula aula) {
		this.setId(aula.getId());
		if (aula.getAlumnos()!=null && !aula.getAlumnos().isEmpty()) {
			HashSet<AlumnoDTO> alumnoDTOs=new HashSet<>();
			for (Alumno alumno : aula.getAlumnos()) {
				alumnoDTOs.add(new AlumnoDTO(alumno));
			}			
			this.setAlumnos(alumnoDTOs);
		}
			 
		
		if (aula.getProfesores()!=null && !aula.getProfesores().isEmpty()) {
			HashSet<ProfesorDTO> profesoresDTOs=new HashSet<>();
			for (Profesor profesor : aula.getProfesores()) {
				profesoresDTOs.add(new ProfesorDTO(profesor));
			}			
			this.setProfesores(profesoresDTOs);
		}
		
		//this.setComentarios(aula.getComentarios());
				
		this.setIdMateria(aula.getMateria().getId());
		this.setIdInstitucion(aula.getInstitucion()!=null?aula.getInstitucion().getId():null);
		this.setDia(DateUtils.toString(aula.getDia()));
		this.setHora(aula.getHora().toString());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<AlumnoDTO> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<AlumnoDTO> alumnos) {
		this.alumnos = alumnos;
	}

	public Set<ProfesorDTO> getProfesores() {
		return profesores;
	}

	public void setProfesores(Set<ProfesorDTO> profesores) {
		this.profesores = profesores;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
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

	
	
	
}

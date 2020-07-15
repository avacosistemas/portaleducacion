package ar.com.avaco.educacion.repository.alumno;

import java.util.List;

import ar.com.avaco.educacion.domain.entities.Alumno;

public interface AlumnoRepositoryCustom {

	Alumno getAlumno(Long id);
	
	List<Alumno> listAlumnos();
	
}

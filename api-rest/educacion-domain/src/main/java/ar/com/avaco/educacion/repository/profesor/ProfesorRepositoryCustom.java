package ar.com.avaco.educacion.repository.profesor;

import java.util.List;

import ar.com.avaco.educacion.domain.entities.Profesor;

public interface ProfesorRepositoryCustom {

	Profesor getProfesor(Long id);

	Profesor getMateriaProfesor(Long id);
	
	List<Profesor> listProfesores();

	List<Profesor> listCatalogoDocente(String campo, boolean desc);
	
}

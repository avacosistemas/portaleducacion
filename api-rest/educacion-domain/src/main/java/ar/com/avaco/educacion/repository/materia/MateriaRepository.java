package ar.com.avaco.educacion.repository.materia;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Materia;

public interface MateriaRepository extends NJRepository<Long, Materia>, MateriaRepositoryCustom {

	
	Materia findByDescripcionEqualsIgnoreCase(String descripcion);
	
}

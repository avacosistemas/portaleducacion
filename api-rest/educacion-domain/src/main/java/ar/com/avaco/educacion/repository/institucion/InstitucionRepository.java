package ar.com.avaco.educacion.repository.institucion;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Institucion;

public interface InstitucionRepository extends NJRepository<Long, Institucion>, InstitucionRepositoryCustom {

	List<Institucion> findAllByAlumnosId(Long id);
	
	Institucion findByNombreEqualsIgnoreCase(String nombre);
	
}

package ar.com.avaco.educacion.repository.nivel;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Institucion;
import ar.com.avaco.educacion.domain.entities.Nivel;

public interface NivelRepository extends NJRepository<Integer, Nivel>, NivelRepositoryCustom {

	Institucion findByDescripcionEqualsIgnoreCase(String descripcion);
	
}

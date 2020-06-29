package ar.com.avaco.educacion.repository.horas.disponibilidad;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Disponibilidad;

public interface DisponibilidadRepository extends NJRepository<Long, Disponibilidad>, DisponibilidadRepositoryCustom {

	
}

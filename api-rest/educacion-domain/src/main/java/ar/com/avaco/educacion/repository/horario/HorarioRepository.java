package ar.com.avaco.educacion.repository.horario;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Horario;


public interface HorarioRepository extends NJRepository<Long, Horario>, HorarioRepositoryCustom {

	
}

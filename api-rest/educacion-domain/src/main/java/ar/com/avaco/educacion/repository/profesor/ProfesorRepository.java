package ar.com.avaco.educacion.repository.profesor;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Profesor;

public interface ProfesorRepository extends NJRepository<Long, Profesor>, ProfesorRepositoryCustom {


}

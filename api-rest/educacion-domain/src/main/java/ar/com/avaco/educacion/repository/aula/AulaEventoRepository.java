package ar.com.avaco.educacion.repository.aula;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.AulaEventos;

public interface AulaEventoRepository extends NJRepository<Long, AulaEventos>, AulaEventosRepositoryCustom {

	
}

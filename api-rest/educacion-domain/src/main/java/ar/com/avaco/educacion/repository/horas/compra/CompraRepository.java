package ar.com.avaco.educacion.repository.horas.compra;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Compra;

public interface CompraRepository extends NJRepository<Long, Compra>, CompraRepositoryCustom {

	
}

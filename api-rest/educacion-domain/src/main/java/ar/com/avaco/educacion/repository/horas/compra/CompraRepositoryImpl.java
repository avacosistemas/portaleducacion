package ar.com.avaco.educacion.repository.horas.compra;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Compra;

@Repository("compraRepository")
public class CompraRepositoryImpl extends NJBaseRepository<Long, Compra> implements CompraRepositoryCustom {

	public CompraRepositoryImpl(EntityManager entityManager) {
		super(Compra.class, entityManager);
	}


}

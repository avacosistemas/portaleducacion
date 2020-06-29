package ar.com.avaco.educacion.repository.decidir;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Decidir;

@Repository("decidirRepository")
public class DecidirRepositoryImpl extends NJBaseRepository<Long, Decidir> implements DecidirRepositoryCustom {

	public DecidirRepositoryImpl(EntityManager entityManager) {
		super(Decidir.class, entityManager);
	}


}

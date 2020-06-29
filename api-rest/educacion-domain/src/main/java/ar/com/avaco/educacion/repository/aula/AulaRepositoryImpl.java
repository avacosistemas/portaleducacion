package ar.com.avaco.educacion.repository.aula;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Aula;

@Repository("aulaRepository")
public class AulaRepositoryImpl extends NJBaseRepository<Long, Aula> implements AulaRepositoryCustom {

	public AulaRepositoryImpl(EntityManager entityManager) {
		super(Aula.class, entityManager);
	}


}

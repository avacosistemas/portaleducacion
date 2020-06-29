package ar.com.avaco.educacion.repository.nivel;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Nivel;

@Repository("nivelRepository")
public class NivelRepositoryImpl extends NJBaseRepository<Integer, Nivel> implements NivelRepositoryCustom {

	public NivelRepositoryImpl(EntityManager entityManager) {
		super(Nivel.class, entityManager);
	}


}

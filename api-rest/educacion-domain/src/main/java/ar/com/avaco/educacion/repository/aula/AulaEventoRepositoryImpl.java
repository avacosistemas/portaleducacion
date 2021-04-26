package ar.com.avaco.educacion.repository.aula;


import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.AulaEventos;

@Repository("aulaEventoRepository")
public class AulaEventoRepositoryImpl extends NJBaseRepository<Long, AulaEventos> {

	public AulaEventoRepositoryImpl(EntityManager entityManager) {
		super(AulaEventos.class, entityManager);
	}

}

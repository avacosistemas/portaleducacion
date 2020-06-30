package ar.com.avaco.educacion.repository.disponibilidad;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;

@Repository("horarioDisponibleRepository")
public class HorarioDisponibleRepositoryImpl extends NJBaseRepository<Long, HorarioDisponible> implements HorarioDisponibleRepositoryCustom {

	public HorarioDisponibleRepositoryImpl(EntityManager entityManager) {
		super(HorarioDisponible.class, entityManager);
	}

}

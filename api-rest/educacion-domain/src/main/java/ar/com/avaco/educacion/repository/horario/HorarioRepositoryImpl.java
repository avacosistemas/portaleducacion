package ar.com.avaco.educacion.repository.horario;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Horario;

@Repository("horarioRepository")
public class HorarioRepositoryImpl extends NJBaseRepository<Long, Horario> implements HorarioRepositoryCustom {

	public HorarioRepositoryImpl(EntityManager entityManager) {
		super(Horario.class, entityManager);
	}


}

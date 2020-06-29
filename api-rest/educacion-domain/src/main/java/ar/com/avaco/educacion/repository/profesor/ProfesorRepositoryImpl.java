package ar.com.avaco.educacion.repository.profesor;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Profesor;

@Repository("profesorRepository")
public class ProfesorRepositoryImpl extends NJBaseRepository<Long, Profesor> implements ProfesorRepositoryCustom {

	public ProfesorRepositoryImpl(EntityManager entityManager) {
		super(Profesor.class, entityManager);
	}

}

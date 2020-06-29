package ar.com.avaco.educacion.repository.institucion;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Institucion;

@Repository("institucionRepository")
public class InstitucionRepositoryImpl extends NJBaseRepository<Long, Institucion> implements InstitucionRepositoryCustom {

	public InstitucionRepositoryImpl(EntityManager entityManager) {
		super(Institucion.class, entityManager);
	}


}

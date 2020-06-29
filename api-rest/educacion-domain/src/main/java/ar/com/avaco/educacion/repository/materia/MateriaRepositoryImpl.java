package ar.com.avaco.educacion.repository.materia;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Materia;

@Repository("materiaRepository")
public class MateriaRepositoryImpl extends NJBaseRepository<Long, Materia> implements MateriaRepositoryCustom {

	public MateriaRepositoryImpl(EntityManager entityManager) {
		super(Materia.class, entityManager);
	}


}

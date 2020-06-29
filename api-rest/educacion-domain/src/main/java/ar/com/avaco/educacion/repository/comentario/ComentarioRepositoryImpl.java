package ar.com.avaco.educacion.repository.comentario;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Comentario;

@Repository("alumnoRepository")
public class ComentarioRepositoryImpl extends NJBaseRepository<Long, Comentario> implements ComentarioRepositoryCustom {

	public ComentarioRepositoryImpl(EntityManager entityManager) {
		super(Comentario.class, entityManager);
	}


}

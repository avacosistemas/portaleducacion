package ar.com.avaco.educacion.repository.pregresp;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;

@Repository("preguntaRespuestaRepository")
public class PreguntaRespuestaRepositoryImpl extends NJBaseRepository<Long, PreguntaRespuesta> implements PreguntaRespuestaRepositoryCustom {

	public PreguntaRespuestaRepositoryImpl(EntityManager entityManager) {
		super(PreguntaRespuesta.class, entityManager);
	}


}

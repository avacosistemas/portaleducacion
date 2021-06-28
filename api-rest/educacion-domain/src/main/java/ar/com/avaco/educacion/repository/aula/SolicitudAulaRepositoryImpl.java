package ar.com.avaco.educacion.repository.aula;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.AulaAlumno;
import ar.com.avaco.educacion.domain.entities.SolicitudAula;

@Repository("solicitudAulaRepository")
public class SolicitudAulaRepositoryImpl extends NJBaseRepository<Long, SolicitudAula> implements SolicitudAulaRepositoryCustom {

	public SolicitudAulaRepositoryImpl(EntityManager entityManager) {
		super(SolicitudAula.class, entityManager);
	}

}

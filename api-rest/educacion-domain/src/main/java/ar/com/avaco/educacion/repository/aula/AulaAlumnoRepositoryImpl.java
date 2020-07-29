package ar.com.avaco.educacion.repository.aula;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.AulaAlumno;

@Repository("aulaAlumnoRepository")
public class AulaAlumnoRepositoryImpl extends NJBaseRepository<Long, AulaAlumno> implements AulaAlumnoRepositoryCustom {

	public AulaAlumnoRepositoryImpl(EntityManager entityManager) {
		super(AulaAlumno.class, entityManager);
	}

}

package ar.com.avaco.educacion.repository.alumno;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Alumno;

@Repository("alumnoRepository")
public class AlumnoRepositoryImpl extends NJBaseRepository<Long, Alumno> implements AlumnoRepositoryCustom {

	public AlumnoRepositoryImpl(EntityManager entityManager) {
		super(Alumno.class, entityManager);
	}


}

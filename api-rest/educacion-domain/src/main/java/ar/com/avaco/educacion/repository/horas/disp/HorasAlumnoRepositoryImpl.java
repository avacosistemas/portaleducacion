package ar.com.avaco.educacion.repository.horas.disp;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.HorasAlumno;

@Repository("horasAlumnoRepository")
public class HorasAlumnoRepositoryImpl extends NJBaseRepository<Long, HorasAlumno> implements HorasAlumnoRepositoryCustom {

	public HorasAlumnoRepositoryImpl(EntityManager entityManager) {
		super(HorasAlumno.class, entityManager);
	}


}

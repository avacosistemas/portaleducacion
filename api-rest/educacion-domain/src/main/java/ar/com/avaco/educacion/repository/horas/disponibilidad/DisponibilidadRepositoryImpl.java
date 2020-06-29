package ar.com.avaco.educacion.repository.horas.disponibilidad;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Disponibilidad;

@Repository("disponibilidadRepository")
public class DisponibilidadRepositoryImpl extends NJBaseRepository<Long, Disponibilidad> implements DisponibilidadRepositoryCustom {

	public DisponibilidadRepositoryImpl(EntityManager entityManager) {
		super(Disponibilidad.class, entityManager);
	}


}

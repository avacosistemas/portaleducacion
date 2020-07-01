package ar.com.avaco.educacion.repository.disponibilidad;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Dia;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;


public interface HorarioDisponibleRepository extends NJRepository<Long, HorarioDisponible>, HorarioDisponibleRepositoryCustom {

	List<HorarioDisponible> findAllByProfesorId(Long idProfesor);

	HorarioDisponible findByDiaAndHoraAndProfesorId(Dia dia, String hora, Long id);


}

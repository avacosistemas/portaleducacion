package ar.com.avaco.educacion.repository.disponibilidad;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;


public interface HorarioDisponibleRepository extends NJRepository<Long, HorarioDisponible>, HorarioDisponibleRepositoryCustom {

	List<HorarioDisponible> findAllByProfesorId(Long idProfesor);

	HorarioDisponible findByDiaAndHoraAndProfesorId(DayOfWeek dia, Integer hora, Long id);
	

   @Query(value = "SELECT hd FROM HorarioDisponible hd WHERE hd.dia = :dia and hd.id = :id")
   List<HorarioDisponible> selectHorariosByDiaAndProfesor(@Param("dia") DayOfWeek dia, @Param("id") Long id);
	
	//List<HorarioDisponible> findByDiaAndProfesorId(DayOfWeek dia, Long idProfesor);


}

package ar.com.avaco.educacion.repository.horas.disp;

import org.springframework.data.jpa.repository.Query;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.HorasAlumno;

public interface HorasAlumnoRepository extends NJRepository<Long, HorasAlumno>, HorasAlumnoRepositoryCustom {

	@Query("FROM HorasAlumno WHERE profesor.id = ?1 and alumno.id=?2")
	HorasAlumno getHorasAlumnoByProfesorAndAlumno(Long idProfesor, Long idAlumno);
}

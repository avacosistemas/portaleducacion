package ar.com.avaco.educacion.service.horas.disp;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.educacion.domain.entities.HorasAlumno;

public interface HorasAlumnoService extends NJService<Long, HorasAlumno> {

	HorasAlumno getByAlumnoYProfesorId(Long idAlumno, Long idProfesor);

	
}

package ar.com.avaco.educacion.repository.alumno;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Alumno;

public interface AlumnoRepository extends NJRepository<Long, Alumno>, AlumnoRepositoryCustom {


	
}

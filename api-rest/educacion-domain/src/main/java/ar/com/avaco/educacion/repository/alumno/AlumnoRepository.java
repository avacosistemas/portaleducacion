package ar.com.avaco.educacion.repository.alumno;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Alumno;

public interface AlumnoRepository extends NJRepository<Long, Alumno>, AlumnoRepositoryCustom {

	List<Alumno> findByInstitucionId(Long idInstitucion);


	
}

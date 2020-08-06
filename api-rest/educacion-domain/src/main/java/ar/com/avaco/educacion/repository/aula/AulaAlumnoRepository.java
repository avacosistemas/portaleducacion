package ar.com.avaco.educacion.repository.aula;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.AulaAlumno;

public interface AulaAlumnoRepository extends NJRepository<Long, AulaAlumno>, AulaAlumnoRepositoryCustom {

	List<AulaAlumno> findAllByAulaId(Long id);

	List<AulaAlumno> findAllByAlumnoId(Long id);

	List<AulaAlumno> findAllByAulaProfesorId(Long id);

	AulaAlumno findOneByAulaIdAndAlumnoId(Long idAula, Long id);
	
}

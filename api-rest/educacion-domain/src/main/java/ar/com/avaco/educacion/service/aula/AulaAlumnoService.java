package ar.com.avaco.educacion.service.aula;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.AulaAlumno;

public interface AulaAlumnoService extends NJService<Long, AulaAlumno> {

	List<AulaAlumno> listByAula(Long idAula);

	AulaAlumno saveAlumno(AulaAlumno aulaAlumno) throws BusinessException;

	List<AulaAlumno> listByProfesorId(Long id);

	List<AulaAlumno> listByAlumnoId(Long id);

	AulaAlumno getByIdAulaIdAlumno(Long idClase, Long id);


}

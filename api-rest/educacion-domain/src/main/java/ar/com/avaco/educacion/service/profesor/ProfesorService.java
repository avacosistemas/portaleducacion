package ar.com.avaco.educacion.service.profesor;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Profesor;

public interface ProfesorService extends NJService<Long, Profesor> {
	
	Profesor getProfesor(Long id);
	
	Profesor getMateriaProfesor(Long id);
	
	List<Profesor> listProfesores();

	Profesor bloquearHabilitarProfesor(Profesor entity, boolean bloquear) throws BusinessException;
	
}

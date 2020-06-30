package ar.com.avaco.educacion.service.materia;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;

public interface MateriaService extends NJService<Long, Materia> {

	List<Materia> listByNivel(Integer idNivel);
	
	List<Materia> listByProfesor(Long idProfesor);
	
	Materia createMateriaProfesor(Long idMateria, Long idProfesor) throws BusinessException;
	
	Materia createMateria(Materia entity) throws BusinessException;
	
	Materia updateMateria(Materia entity) throws BusinessException;
	
}

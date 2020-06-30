package ar.com.avaco.educacion.service.institucion;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Institucion;

public interface InstitucionService extends NJService<Long, Institucion> {

	List<Institucion> listByAlumno(Long idAlumno);
	
	Institucion createInstitucion(Institucion entity) throws BusinessException;
	
	Institucion updateInstitucion(Institucion entity) throws BusinessException;
	
}

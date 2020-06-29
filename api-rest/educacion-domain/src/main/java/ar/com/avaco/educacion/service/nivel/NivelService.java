package ar.com.avaco.educacion.service.nivel;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Nivel;

public interface NivelService extends NJService<Integer, Nivel> {

	Nivel createNivel(Nivel entity) throws BusinessException;
	
	Nivel updateNivel(Nivel entity) throws BusinessException;
	
}

package ar.com.avaco.educacion.ws.service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.NivelDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface NivelEPService extends CRUDEPService<Integer, NivelDTO> {

	NivelDTO createNivel(NivelDTO nivelDto) throws BusinessException;
	
	NivelDTO updateNivel(Integer id, NivelDTO nivelDto) throws BusinessException;

}

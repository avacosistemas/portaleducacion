package ar.com.avaco.educacion.ws.service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.InstitucionDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface InstitucionEPService extends CRUDEPService<Long, InstitucionDTO> {

	InstitucionDTO createInstitucion(InstitucionDTO institucionDto) throws BusinessException;
	
	InstitucionDTO updateInstitucion(Long id, InstitucionDTO institucionDto) throws BusinessException;

}

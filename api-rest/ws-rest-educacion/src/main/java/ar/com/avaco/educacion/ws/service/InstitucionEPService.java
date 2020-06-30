package ar.com.avaco.educacion.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.InstitucionDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface InstitucionEPService extends CRUDEPService<Long, InstitucionDTO> {

	List<InstitucionDTO> listByAlumno(Long idAlumno);
	
	InstitucionDTO createInstitucion(InstitucionDTO institucionDto) throws BusinessException;
	
	InstitucionDTO updateInstitucion(Long id, InstitucionDTO institucionDto) throws BusinessException;

}

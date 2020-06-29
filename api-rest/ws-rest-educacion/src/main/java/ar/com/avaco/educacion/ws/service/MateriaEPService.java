package ar.com.avaco.educacion.ws.service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.MateriaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface MateriaEPService extends CRUDEPService<Long, MateriaDTO> {

	MateriaDTO createMateria(MateriaDTO materiaDto) throws BusinessException;
	
	MateriaDTO updateMateria(Long id, MateriaDTO materiaDto) throws BusinessException;

}

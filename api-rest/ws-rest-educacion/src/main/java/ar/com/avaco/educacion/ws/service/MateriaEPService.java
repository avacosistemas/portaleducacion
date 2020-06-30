package ar.com.avaco.educacion.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.MateriaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface MateriaEPService extends CRUDEPService<Long, MateriaDTO> {

	List<MateriaDTO> listByNivel(Integer idNivel);
	
	MateriaDTO createMateria(MateriaDTO materiaDto) throws BusinessException;

	MateriaDTO updateMateria(Long id, MateriaDTO materiaDto) throws BusinessException;

}

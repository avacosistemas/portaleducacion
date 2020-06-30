package ar.com.avaco.educacion.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.MateriaDTO;
import ar.com.avaco.educacion.ws.dto.MateriaProfesorDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface MateriaProfesorEPService extends CRUDEPService<Long, MateriaProfesorDTO> {

	List<MateriaProfesorDTO> listByProfesor(Long idProfesor);
	
	MateriaProfesorDTO createMateriaProfesor(Long idMateria, Long idProfesor) throws BusinessException;

}

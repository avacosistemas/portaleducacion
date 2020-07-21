package ar.com.avaco.educacion.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.MateriaProfesorDTO;
import ar.com.avaco.educacion.ws.dto.MateriaProfesorFullDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface MateriaProfesorEPService extends CRUDEPService<Long, MateriaProfesorFullDTO> {

	List<MateriaProfesorFullDTO> listMateriasProfesor(Long idProfesor);
	
	MateriaProfesorDTO createMateriaProfesor(MateriaProfesorDTO materiaProfesor) throws BusinessException;
	
	void removeMateriaProfesor(Long idProfesor, Long idMateria) throws BusinessException;

}

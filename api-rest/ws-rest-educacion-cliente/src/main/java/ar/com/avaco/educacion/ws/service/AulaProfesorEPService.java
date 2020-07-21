package ar.com.avaco.educacion.ws.service;


import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.AulaProfesorDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface AulaProfesorEPService extends CRUDEPService<Long, AulaProfesorDTO> {

	AulaProfesorDTO addProfesor(AulaProfesorDTO aulaProfesorDTO) throws BusinessException;

	void removeAulaProfesor(Long idAula, Long idProfesor) throws BusinessException;

	List<AulaProfesorDTO> listAulaProfesor(Long idAula) throws BusinessException;

	
	

}

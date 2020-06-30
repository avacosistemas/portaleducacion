package ar.com.avaco.educacion.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.ProfesorDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface ProfesorEPService extends CRUDEPService<Long, ProfesorDTO> {

	ProfesorDTO getProfesor(Long id);
	
	List<ProfesorDTO> listProfesores();
	
	ProfesorDTO bloquearHabilitarProfesor(Long id, boolean bloquear) throws BusinessException;

	
}

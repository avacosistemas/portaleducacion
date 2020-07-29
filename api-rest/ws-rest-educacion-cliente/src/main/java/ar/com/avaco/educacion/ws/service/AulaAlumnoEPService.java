package ar.com.avaco.educacion.ws.service;

import java.util.List;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.AulaAlumnoDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface AulaAlumnoEPService extends CRUDEPService<Long, AulaAlumnoDTO> {

	//	AulaAlumnoDTO addAlumno(AulaAlumnoDTO aulaAlumnoDTO) throws BusinessException;

	//	void removeAulaAlumno(Long idAula, Long idAlumno) throws BusinessException;

	List<AulaAlumnoDTO> listAulaAlumno(Long idAula) throws BusinessException;

	
	

}

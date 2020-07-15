package ar.com.avaco.educacion.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.AlumnoDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface AlumnoEPService extends CRUDEPService<Long, AlumnoDTO> {

	AlumnoDTO getAlumno(Long id);
	
	List<AlumnoDTO> listAlumnos();
	
	AlumnoDTO updateAlumno(Long id, AlumnoDTO alumnoDto) throws BusinessException;
	
	AlumnoDTO createAlumno(AlumnoDTO alumnoDto) throws BusinessException;
	
	AlumnoDTO bloquearHabilitarAlumno(Long id, boolean bloquear) throws BusinessException;


	
}

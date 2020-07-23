package ar.com.avaco.educacion.ws.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.ProfesorDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface ProfesorEPService extends CRUDEPService<Long, ProfesorDTO> {

	ProfesorDTO getProfesor(Long id);
	
	List<ProfesorDTO> listProfesores();
	
	ProfesorDTO updateProfesor(Long id, ProfesorDTO profesorDto) throws BusinessException;

	ProfesorDTO createProfesor(ProfesorDTO profesorDTO) throws BusinessException;
	
	ProfesorDTO bloquearHabilitarProfesor(Long id, boolean bloquear) throws BusinessException;

	ProfesorDTO updateFotoPerfil(Long id, MultipartFile file) throws BusinessException;
	
	byte[] downloadFotoPerfil(Long id) throws BusinessException;

}

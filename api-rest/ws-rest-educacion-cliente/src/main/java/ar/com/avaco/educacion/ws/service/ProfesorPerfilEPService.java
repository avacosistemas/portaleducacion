package ar.com.avaco.educacion.ws.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.AulaProfesorDTO;
import ar.com.avaco.educacion.ws.dto.ComentarioDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaRespuestaDTO;
import ar.com.avaco.educacion.ws.dto.ProfesorPerfilDTO;
import ar.com.avaco.educacion.ws.dto.RespuestaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface ProfesorPerfilEPService extends CRUDEPService<Long, ProfesorPerfilDTO> {

	ProfesorPerfilDTO getProfesor(Long id);
	
	ProfesorPerfilDTO updateProfesor(Long id, ProfesorPerfilDTO profesorDto) throws BusinessException;
	
	void updateFotoPerfil(Long id, MultipartFile file) throws BusinessException;

	byte[] downloadFotoPerfil(Long id) throws BusinessException;

	List<PreguntaRespuestaDTO> listPreguntaRespuestas(Long idProfesor);

	void responderPregunta(RespuestaDTO respuestaDTO);

	List<AulaProfesorDTO> listarMisAulas(Long id);

	AulaProfesorDTO getAula(Long idClase);

	List<ComentarioDTO> getAnotacionesAula(Long idClase);
	
}

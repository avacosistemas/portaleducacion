package ar.com.avaco.educacion.ws.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.AulaProfesorPortalDTO;
import ar.com.avaco.educacion.ws.dto.CalificacionDTO;
import ar.com.avaco.educacion.ws.dto.ComentarioDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaRespuestaAulaDTO;
import ar.com.avaco.educacion.ws.dto.ProfesorPerfilDTO;
import ar.com.avaco.educacion.ws.dto.RespuestaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface ProfesorPerfilEPService extends CRUDEPService<Long, ProfesorPerfilDTO> {

	ProfesorPerfilDTO getProfesor(Long id);
	
	ProfesorPerfilDTO updateProfesor(Long id, ProfesorPerfilDTO profesorDto) throws BusinessException;
	
	void updateFotoPerfil(Long id, MultipartFile file) throws BusinessException;

	byte[] downloadFotoPerfil(Long id) throws BusinessException;

	List<PreguntaRespuestaAulaDTO> listPreguntaRespuestas(Long idProfesor);

	void responderPregunta(RespuestaDTO respuestaDTO);

	List<AulaProfesorPortalDTO> listarMisAulas(Long id);

	AulaProfesorPortalDTO getAula(Long idClase);

	List<ComentarioDTO> getComentariosAula(Long idClase);

	void agregarComentarioAula(ComentarioDTO comentarioDTO, Long idAula, String nombre);

	List<CalificacionDTO> getCalificaciones(Long id);

	List<CalificacionDTO> getAlumnos(Long idClase);
	
}

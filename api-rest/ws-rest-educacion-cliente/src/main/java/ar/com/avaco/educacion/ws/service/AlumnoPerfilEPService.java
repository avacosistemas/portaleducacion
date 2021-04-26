package ar.com.avaco.educacion.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.AlumnoPerfilDTO;
import ar.com.avaco.educacion.ws.dto.AulaAbiertaInstitucionDTO;
import ar.com.avaco.educacion.ws.dto.AulaAlumnoPortalDTO;
import ar.com.avaco.educacion.ws.dto.ComentarioDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaDTO;
import ar.com.avaco.educacion.ws.dto.PuntuacionDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface AlumnoPerfilEPService extends CRUDEPService<Long, AlumnoPerfilDTO> {

	AlumnoPerfilDTO getAlumno(Long id);

	void updateAlumno(Long id, AlumnoPerfilDTO perfil) throws BusinessException;

	void preguntar(PreguntaDTO preguntaDTO, Long idAlumno);

	void agregarComentarioAula(ComentarioDTO comentario, Long idClase, String nombreApellido);

	List<AulaAlumnoPortalDTO> listarMisAulas(Long id);

	List<ComentarioDTO> getComentariosAula(Long idClase);

	void calificarAula(Long idClase, Long id, PuntuacionDTO puntuacionDTO) throws BusinessException;

	AulaAlumnoPortalDTO getAula(Long idClase, Long idAlumno);

	List<AulaAbiertaInstitucionDTO> listarAulasAbiertesMiInstitucion(Long idAlumno);

	void solicitarUnirse(Long idAula, Long idAlumno);

}

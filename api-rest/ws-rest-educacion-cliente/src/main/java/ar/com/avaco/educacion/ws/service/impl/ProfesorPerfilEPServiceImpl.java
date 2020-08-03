package ar.com.avaco.educacion.ws.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Identificacion;
import ar.com.avaco.educacion.domain.entities.cliente.TipoIdentificacion;
import ar.com.avaco.educacion.service.aula.AulaService;
import ar.com.avaco.educacion.service.pregresp.PreguntaRespuestaService;
import ar.com.avaco.educacion.service.profesor.ProfesorService;
import ar.com.avaco.educacion.ws.dto.AulaDTO;
import ar.com.avaco.educacion.ws.dto.AulaProfesorDTO;
import ar.com.avaco.educacion.ws.dto.ComentarioDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaRespuestaDTO;
import ar.com.avaco.educacion.ws.dto.ProfesorPerfilDTO;
import ar.com.avaco.educacion.ws.dto.RespuestaDTO;
import ar.com.avaco.educacion.ws.service.ProfesorPerfilEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("profesorPerfilEPService")
public class ProfesorPerfilEPServiceImpl extends CRUDEPBaseService<Long, ProfesorPerfilDTO, Profesor, ProfesorService> implements ProfesorPerfilEPService {

	private PreguntaRespuestaService preguntaRespuestaService;
	
	private AulaService aulaService;
	
	@Override
	public ProfesorPerfilDTO getProfesor(Long id) {
		Profesor profesor = this.getService().getProfesor(id);
		ProfesorPerfilDTO profesorDTO = new ProfesorPerfilDTO(profesor);
		return profesorDTO;
	}
	
	@Override
	public ProfesorPerfilDTO updateProfesor(Long id, ProfesorPerfilDTO profesorDto) throws BusinessException {
		Profesor profesor = profesorDto.toEntity();
		profesor.setId(id);
		profesor = service.updateProfesor(profesor);
		return new ProfesorPerfilDTO(profesor);
	}
	
	@Override
	public void updateFotoPerfil(Long id, MultipartFile file) throws BusinessException {
		service.uploadFotoPerfil(id, file);
	}
	
	@Override
	public byte[] downloadFotoPerfil(Long id) throws BusinessException {
		return service.downloadFotoPerfil(id);
	}
	
	@Override
	protected Profesor convertToEntity(ProfesorPerfilDTO dto) {
		
		Profesor profesor = new Profesor();
		profesor.setId(dto.getId());
		profesor.setNombre(dto.getNombre());
		profesor.setApellido(dto.getApellido());
	
		Identificacion id = new Identificacion();
		id.setTipo(TipoIdentificacion.valueOf(dto.getTipoIdentificacion()));
		id.setNumero(dto.getNumeroIdentificacion());
		id.setCliente(profesor);
		profesor.setIdentificacion(id);
		
		Contacto contacto = new Contacto();
		contacto.setTelefonoMovil(dto.getTelefonoMovil());
		contacto.setCliente(profesor);
		profesor.setContacto(contacto);
	
		profesor.setUsername(dto.getUsername());
		profesor.setEmail(dto.getEmail());
	
		return profesor;
	}

	@Override
	protected ProfesorPerfilDTO convertToDto(Profesor entity) {
		ProfesorPerfilDTO profesorDTO = new ProfesorPerfilDTO();
		profesorDTO.setId(entity.getId());
		profesorDTO.setNombre(entity.getNombre());
		profesorDTO.setApellido(entity.getApellido());
		profesorDTO.setTipoIdentificacion(entity.getIdentificacion().getTipo().name());
		profesorDTO.setNumeroIdentificacion(entity.getIdentificacion().getNumero());
		profesorDTO.setUsername(entity.getUsername());
		profesorDTO.setEmail(entity.getEmail());
		profesorDTO.setTelefonoMovil(entity.getContacto().getTelefonoMovil());
		return profesorDTO;
	}

	@Override
	public List<PreguntaRespuestaDTO> listPreguntaRespuestas(Long idProfesor) {
		List<PreguntaRespuesta> listByProfesor = preguntaRespuestaService.listByProfesor(idProfesor);
		List<PreguntaRespuestaDTO> list = new ArrayList<>();
		listByProfesor.forEach(pr -> list.add(new PreguntaRespuestaDTO(pr)));
		return list;
	}

	@Override
	public void responderPregunta(RespuestaDTO respuestaDTO) {
		PreguntaRespuesta preguntaRespuesta = this.preguntaRespuestaService.get(respuestaDTO.getIdPreguntaRespuesta());
		preguntaRespuesta.setRespuesta(respuestaDTO.getRespuesta());
		preguntaRespuesta.setFechaRespuesta(Calendar.getInstance().getTime());
		this.preguntaRespuestaService.update(preguntaRespuesta);
	}
	
	@Override
	public List<AulaProfesorDTO> listarMisAulas(Long id) {
		List<Aula> aulas = aulaService.listByProfesorId(id);
		List<AulaProfesorDTO> aulasDTO = new ArrayList<>();
		aulas.stream().forEach(x->aulasDTO.add(new AulaProfesorDTO(x)));
		return aulasDTO;
	}
	
	@Override
	public AulaProfesorDTO getAula(Long idClase) {
		Aula aula = aulaService.get(idClase);
		AulaProfesorDTO apdto = new AulaProfesorDTO(aula);
		return apdto;
	}
	
	@Override
	public List<ComentarioDTO> getAnotacionesAula(Long idClase) {
		// TODO Auto-generated method stub
		return null;
	}

	//Service
	@Override
	@Resource(name = "profesorService")
	protected void setService(ProfesorService profesorService) {
		this.service = profesorService;
	}

	@Resource(name = "preguntaRespuestaService")
	public void setPreguntaRespuestaService(PreguntaRespuestaService preguntaRespuestaService) {
		this.preguntaRespuestaService = preguntaRespuestaService;
	}
	
	@Resource(name = "aulaService")
	public void setAulaService(AulaService aulaService) {
		this.aulaService = aulaService;
	}
	
}

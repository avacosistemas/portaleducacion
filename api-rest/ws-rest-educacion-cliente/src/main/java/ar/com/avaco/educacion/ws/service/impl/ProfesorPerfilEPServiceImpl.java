package ar.com.avaco.educacion.ws.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.AulaAlumno;
import ar.com.avaco.educacion.domain.entities.Comentario;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Identificacion;
import ar.com.avaco.educacion.domain.entities.cliente.TipoIdentificacion;
import ar.com.avaco.educacion.service.aula.AulaAlumnoService;
import ar.com.avaco.educacion.service.aula.AulaService;
import ar.com.avaco.educacion.service.comentario.ComentarioService;
import ar.com.avaco.educacion.service.pregresp.PreguntaRespuestaService;
import ar.com.avaco.educacion.service.profesor.ProfesorService;
import ar.com.avaco.educacion.ws.dto.AulaProfesorPortalDTO;
import ar.com.avaco.educacion.ws.dto.CalificacionDTO;
import ar.com.avaco.educacion.ws.dto.ComentarioDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaRespuestaAulaDTO;
import ar.com.avaco.educacion.ws.dto.ProfesorPerfilDTO;
import ar.com.avaco.educacion.ws.dto.RespuestaDTO;
import ar.com.avaco.educacion.ws.service.ProfesorPerfilEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("profesorPerfilEPService")
public class ProfesorPerfilEPServiceImpl extends CRUDEPBaseService<Long, ProfesorPerfilDTO, Profesor, ProfesorService> implements ProfesorPerfilEPService {

	private PreguntaRespuestaService preguntaRespuestaService;
	
	private AulaService aulaService;
	
	private ComentarioService comentarioService;
	
	private AulaAlumnoService aulaAlumnoService;
	
	@Override
	public ProfesorPerfilDTO getProfesor(Long id) {
		Profesor profesor = this.getService().getProfesor(id);
		ProfesorPerfilDTO profesorDTO = convertToDto(profesor);
		return profesorDTO;
	}
	
	@Override
	public ProfesorPerfilDTO updateProfesor(Long id, ProfesorPerfilDTO profesorDto) throws BusinessException {
		Profesor profesor = service.get(id);
		
		profesor.setNombre(profesorDto.getNombre());
		profesor.setApellido(profesorDto.getApellido());
		profesor.setEmail(profesorDto.getEmail());
		profesor.setDescripcion(profesorDto.getDescripcion());
		profesor.setTitulo(profesorDto.getTitulo());
		if (profesorDto.getFoto() != null) {
			profesor.setFoto(profesorDto.getFoto());
		}

		Identificacion ident = profesor.getIdentificacion();
		ident.setNumero(profesorDto.getNumeroIdentificacion());
		ident.setTipo(TipoIdentificacion.DNI);
		ident.setCliente(profesor);
		profesor.setIdentificacion(ident);
		
		Contacto contacto = profesor.getContacto();
		contacto.setTelefonoMovil(profesorDto.getTelefonoMovil());
		contacto.setTelefonoFijo(profesorDto.getTelefonoFijo());
		contacto.setCliente(profesor);
		profesor.setContacto(contacto);
		
		profesor = service.updateProfesor(profesor);
		return convertToDto(profesor);
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
	protected ProfesorPerfilDTO convertToDto(Profesor profesor) {
		ProfesorPerfilDTO dto = new ProfesorPerfilDTO();
		dto.setId(profesor.getId());
		dto.setNombre(profesor.getNombre());
		dto.setApellido(profesor.getApellido());
		dto.setTipoIdentificacion(profesor.getIdentificacion().getTipo().name());
		dto.setNumeroIdentificacion(profesor.getIdentificacion().getNumero());
		dto.setUsername(profesor.getUsername());
		dto.setEmail(profesor.getEmail());
		dto.setTelefonoMovil(profesor.getContacto().getTelefonoMovil());
		dto.setTelefonoFijo(profesor.getContacto().getTelefonoFijo());
		dto.setCalificacion(profesor.getCalificacion());
		dto.setDescripcion(profesor.getDescripcion());
		dto.setTitulo(profesor.getTitulo());
		dto.setFoto(profesor.getFoto());
		return dto;
	}

	@Override
	public List<PreguntaRespuestaAulaDTO> listPreguntaRespuestas(Long idProfesor) {
		List<PreguntaRespuesta> listByProfesor = preguntaRespuestaService.listByProfesor(idProfesor);
		List<PreguntaRespuestaAulaDTO> list = new ArrayList<>();
		listByProfesor.stream().sorted(Comparator.comparing(PreguntaRespuesta::getFechaPregunta).reversed()).forEach(pr -> list.add(new PreguntaRespuestaAulaDTO(pr)));
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
	public List<AulaProfesorPortalDTO> listarMisAulas(Long id) {
		List<Aula> aulas = aulaService.listByProfesorId(id);
		List<AulaProfesorPortalDTO> aulasDTO = new ArrayList<>();
		Comparator<Aula> comparaAula = Comparator.comparing(Aula::getDia).thenComparing(Aula::getHora);
		aulas.stream().sorted(comparaAula.reversed()).forEach(x->aulasDTO.add(new AulaProfesorPortalDTO(x)));
		return aulasDTO;
	}
	
	@Override
	public AulaProfesorPortalDTO getAula(Long idClase) {
		Aula aula = aulaService.get(idClase);
		AulaProfesorPortalDTO apdto = new AulaProfesorPortalDTO(aula);
		return apdto;
	}
	
	@Override
	public List<ComentarioDTO> getComentariosAula(Long idClase) {
		List<Comentario> comentarios = comentarioService.listByClaseId(idClase);
		List<ComentarioDTO> comentariosDTO = new ArrayList<>();
		comentarios.forEach(x->comentariosDTO.add(new ComentarioDTO(x)));
		return comentariosDTO;
	}

	@Override
	public void agregarComentarioAula(ComentarioDTO comentarioDTO, Long idAula, String nombre) {
		Comentario comentario = new Comentario();
		Aula aula = new Aula();
		aula.setId(idAula);
		comentario.setAula(aula);
		comentario.setComentario(comentarioDTO.getComentario());
		comentario.setFecha(Calendar.getInstance().getTime());
		comentario.setNombre(nombre);
		comentarioService.save(comentario);
	}

	@Override
	public List<CalificacionDTO> getCalificaciones(Long id) {
		List<AulaAlumno> aulaAlumnoList = aulaAlumnoService.listByProfesorId(id);
		List<CalificacionDTO> calificaciones = new ArrayList<>();
		aulaAlumnoList.forEach(x->calificaciones.add(new CalificacionDTO(x)));
		return calificaciones;
	}

	@Override
	public List<CalificacionDTO> getAlumnos(Long idClase) {
		List<AulaAlumno> aulaAlumnoList = aulaAlumnoService.listByAula(idClase);
		List<CalificacionDTO> calificaciones = new ArrayList<>();
		aulaAlumnoList.forEach(x->calificaciones.add(new CalificacionDTO(x)));
		return calificaciones;
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
	
	@Resource(name = "comentarioService")
	public void setComentarioService(ComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}
	
	@Resource(name = "aulaAlumnoService")
	public void setAulaAlumnoService(AulaAlumnoService aulaAlumnoService) {
		this.aulaAlumnoService = aulaAlumnoService;
	}
	
}

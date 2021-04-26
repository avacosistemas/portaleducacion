package ar.com.avaco.educacion.ws.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.OptionalDouble;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.AulaAlumno;
import ar.com.avaco.educacion.domain.entities.Comentario;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.service.alumno.AlumnoService;
import ar.com.avaco.educacion.service.aula.AulaAlumnoService;
import ar.com.avaco.educacion.service.aula.AulaService;
import ar.com.avaco.educacion.service.comentario.ComentarioService;
import ar.com.avaco.educacion.service.pregresp.PreguntaRespuestaService;
import ar.com.avaco.educacion.service.profesor.ProfesorService;
import ar.com.avaco.educacion.ws.dto.AlumnoPerfilDTO;
import ar.com.avaco.educacion.ws.dto.AulaAbiertaInstitucionDTO;
import ar.com.avaco.educacion.ws.dto.AulaAlumnoPortalDTO;
import ar.com.avaco.educacion.ws.dto.ComentarioDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaDTO;
import ar.com.avaco.educacion.ws.dto.PuntuacionDTO;
import ar.com.avaco.educacion.ws.service.AlumnoPerfilEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("alumnoPerfilEPService")
public class AlumnoPerfilEPServiceImpl extends CRUDEPBaseService<Long, AlumnoPerfilDTO, Alumno, AlumnoService>
		implements AlumnoPerfilEPService {

	private PreguntaRespuestaService preguntaRespuestaService;
	private ComentarioService comentarioService;
	private AulaService aulaService;
	private AulaAlumnoService aulaAlumnoService;
	private ProfesorService profesorService;
	private AlumnoService alumnoService;
	
	// Service
	@Override
	@Resource(name = "alumnoService")
	protected void setService(AlumnoService alumnoService) {
		this.service = alumnoService;
	}

	@Override
	public AlumnoPerfilDTO getAlumno(Long id) {
		Alumno alumno = this.service.get(id);
		AlumnoPerfilDTO apdto = new AlumnoPerfilDTO(alumno);
		return apdto;
	}

	@Override
	public void updateAlumno(Long id, AlumnoPerfilDTO perfil) throws BusinessException {
		Alumno alumno = this.service.get(id);
		alumno.setApellido(perfil.getApellido());
		alumno.getContacto().setTelefonoFijo(perfil.getTelefonoFijo());
		alumno.getContacto().setTelefonoMovil(perfil.getTelefonoMovil());
		alumno.setEmail(perfil.getEmail());
		alumno.setNombre(perfil.getNombre());
		alumno.getIdentificacion().setNumero(perfil.getNumeroIdentificacion());
		this.service.updateAlumno(alumno);
	}

	@Override
	public void preguntar(PreguntaDTO preguntaDTO, Long idAlumno) {
		PreguntaRespuesta pr = new PreguntaRespuesta();

		Alumno alumno = new Alumno();
		alumno.setId(idAlumno);
		pr.setAlumno(alumno);
		pr.setFechaPregunta(Calendar.getInstance().getTime());
		pr.setPregunta(preguntaDTO.getPregunta());

		Profesor profesor = new Profesor();
		profesor.setId(preguntaDTO.getIdProfesor());
		pr.setProfesor(profesor);

		preguntaRespuestaService.save(pr);
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
	public List<ComentarioDTO> getComentariosAula(Long idClase) {
		List<Comentario> comentarios = comentarioService.listByClaseId(idClase);
		List<ComentarioDTO> comentariosDTO = new ArrayList<>();
		comentarios.forEach(x->comentariosDTO.add(new ComentarioDTO(x)));
		return comentariosDTO;
	}
	
	@Override
	public List<AulaAlumnoPortalDTO> listarMisAulas(Long id) {
		List<AulaAlumno> aulas = aulaAlumnoService.listByAlumnoId(id);
		List<AulaAlumnoPortalDTO> aulasDTO = new ArrayList<>();
		if (aulas !=null && !aulas.isEmpty()) {
			aulas.stream().forEach(x->aulasDTO.add(new AulaAlumnoPortalDTO(x)));
		}
		return aulasDTO;
	}
	
	@Override
	public AulaAlumnoPortalDTO getAula(Long idClase, Long idAlumno) {
		AulaAlumno aulaAlumno = aulaAlumnoService.getByIdAulaIdAlumno(idClase, idAlumno);
		AulaAlumnoPortalDTO apdto = new AulaAlumnoPortalDTO(aulaAlumno);
		return apdto;
	}

	@Override
	public void calificarAula(Long idAula, Long id, PuntuacionDTO puntuacionDTO) throws BusinessException {
		AulaAlumno aa = aulaAlumnoService.getByIdAulaIdAlumno(idAula, id);
		
		if (aa.getCalificacion() != null) {
			throw new BusinessException("El aula ya cuenta con una calificación.");
		}
		
		if (puntuacionDTO.getPuntuacion() == null || puntuacionDTO.getPuntuacion() > 5 || puntuacionDTO.getPuntuacion() < 1) {
			throw new BusinessException("La calificación debe estar entre 1 y 5.");
		}

		if (StringUtils.isBlank(puntuacionDTO.getComentario()) || puntuacionDTO.getComentario().length() > 200 || puntuacionDTO.getComentario().length() < 2) {
			throw new BusinessException("El comentario debe tener entre 2 y 200 caracteres.");
		}
		
		aa.setCalificacion(puntuacionDTO.getPuntuacion().doubleValue());
		aa.setComentario(puntuacionDTO.getComentario());
		aulaAlumnoService.update(aa);

		List<AulaAlumno> alumnos = aulaAlumnoService.listByAula(aa.getAula().getId());
		OptionalDouble avgAula = alumnos.stream().filter(x -> x.getCalificacion() != null && x.getCalificacion() > 0D).mapToDouble(AulaAlumno::getCalificacion).average(); 
		
		Aula aula = aa.getAula();
		aula.setCalificacion(avgAula.getAsDouble());
		aulaService.update(aula);
		
		List<Aula> aulasProfesor = aulaService.listByProfesorId(aula.getProfesor().getId());
		OptionalDouble avgProfesor = aulasProfesor.stream().filter(x -> x.getCalificacion() != null && x.getCalificacion() > 0D).mapToDouble(Aula::getCalificacion).average();
		Profesor profesor = aula.getProfesor();
		profesor.setCalificacion(avgProfesor.getAsDouble());
		profesorService.update(profesor);
	}
	
	@Override
	public List<AulaAbiertaInstitucionDTO> listarAulasAbiertesMiInstitucion(Long idAlumno) {
		Long idInstitucion = alumnoService.get(idAlumno).getInstitucion().getId();
		List<Aula> aulasAbiertasInstitucion = aulaService.getAulasAbiertasInstitucion(idInstitucion);
		List<AulaAbiertaInstitucionDTO> aulasDTOS = new ArrayList<>();
		for (Aula aula : aulasAbiertasInstitucion) {
			aulasDTOS.add(new AulaAbiertaInstitucionDTO(aula));
		}
		return aulasDTOS;
	}
	
	@Override
	public void solicitarUnirse(Long idAula, Long idAlumno) {
		this.aulaService.notificarSolicitudUnion(idAula, idAlumno);
	}
	
	@Override
	protected Alumno convertToEntity(AlumnoPerfilDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AlumnoPerfilDTO convertToDto(Alumno entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Resource(name = "preguntaRespuestaService")
	public void setPreguntaRespuestaService(PreguntaRespuestaService preguntaRespuestaService) {
		this.preguntaRespuestaService = preguntaRespuestaService;
	}

	@Resource(name = "comentarioService")
	public void setComentarioService(ComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}
	
	@Resource(name = "aulaService")
	public void setAulaService(AulaService aulaService) {
		this.aulaService = aulaService;
	}

	@Resource(name = "aulaAlumnoService")
	public void setAulaAlumnoService(AulaAlumnoService aulaAlumnoService) {
		this.aulaAlumnoService = aulaAlumnoService;
	}
	
	@Resource(name = "profesorService")
	public void setProfesorService(ProfesorService profesorService) {
		this.profesorService = profesorService;
	}

	@Resource(name = "alumnoService")
	public void setAlumnoService(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}
	
}

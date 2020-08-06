package ar.com.avaco.educacion.ws.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

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
import ar.com.avaco.educacion.ws.dto.AlumnoPerfilDTO;
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
		alumno.getContacto().setTelefonoFijo(perfil.getTelefonoMovil());
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
		aulas.stream().forEach(x->aulasDTO.add(new AulaAlumnoPortalDTO(x)));
		return aulasDTO;
	}
	
	@Override
	public AulaAlumnoPortalDTO getAula(Long idClase) {
		Aula aula = aulaService.get(idClase);
		AulaAlumnoPortalDTO apdto = new AulaAlumnoPortalDTO(aula);
		return apdto;
	}

	@Override
	public void calificarAula(Long idAula, Long id, PuntuacionDTO puntuacionDTO) {
		AulaAlumno aa = aulaAlumnoService.getByIdAulaIdAlumno(idAula, id);
		aa.setCalificacion(puntuacionDTO.getPuntuacion());
		aa.setComentario(puntuacionDTO.getComentario());
		aulaAlumnoService.update(aa);
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
	
}

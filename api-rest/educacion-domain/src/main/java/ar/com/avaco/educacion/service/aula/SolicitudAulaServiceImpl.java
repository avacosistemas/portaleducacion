package ar.com.avaco.educacion.service.aula;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.AulaAlumno;
import ar.com.avaco.educacion.domain.entities.EstadoSolicitudAula;
import ar.com.avaco.educacion.domain.entities.SolicitudAula;
import ar.com.avaco.educacion.repository.aula.SolicitudAulaRepository;
import ar.com.avaco.educacion.service.alumno.AlumnoService;
import ar.com.avaco.educacion.service.notificacion.NotificacionService;

@Transactional
@Service("solicitudAulaService")
public class SolicitudAulaServiceImpl extends NJBaseService<Long, SolicitudAula, SolicitudAulaRepository> implements SolicitudAulaService {

	private NotificacionService notificacionService;
	private AlumnoService alumnoService;
	private AulaAlumnoService aulaAlumnoService;
	private AulaService aulaService;

	@Override
	public void notificarSolicitudUnion(Long idAula, Long idAlumno) {
		Aula aula = this.aulaService.getAula(idAula);
		Alumno alumno = this.alumnoService.getAlumno(idAlumno);
		this.notificacionService.notificarSolicitudUnion(aula, alumno);
	}

	@Override
	public void notificarRechazoSolicitudUnion(Long idAula, Long idAlumno, String motivo) {
		Aula aula = this.aulaService.getAula(idAula);
		Alumno alumno = this.alumnoService.getAlumno(idAlumno);
		this.notificacionService.notificarRechazoUnionAula(aula, alumno, motivo);
	}
	
	@Override
	public void notificarRechazoSolicitudUnion(Long idSolicitud, String motivo) {
		SolicitudAula sa = this.repository.findOne(idSolicitud);
		this.notificacionService.notificarRechazoUnionAula(sa.getAula(), sa.getAlumno(), motivo);
	}
	
	@Override
	public void saveSolicitud(Long idAula, Long idAlumno) throws BusinessException {
		
		if (this.repository.existsByAulaIdAndAlumnoId(idAula, idAlumno)) {
			throw new BusinessException("Ya se ha realizado esta solicitud");
		}
		
		aulaAlumnoService.validarDisponibilidadAula(idAula, idAlumno);
		
		SolicitudAula sa = new SolicitudAula();
		Alumno alumno = new Alumno();
		alumno.setId(idAlumno);
		
		Aula aula = new Aula();
		aula.setId(idAula);
		
		sa.setAlumno(alumno);
		sa.setAula(aula);
		sa.setEstado(EstadoSolicitudAula.PENDIENTE);
		sa.setFechaSolicitud(Calendar.getInstance().getTime());

		this.repository.save(sa);
	}
	
	@Override
	public List<SolicitudAula> listSolicitudesPendientes() {
		return this.repository.findAllByEstadoOrderByFechaSolicitudAsc(EstadoSolicitudAula.PENDIENTE);
	}
	
	@Override
	public List<SolicitudAula> listSolicitudesFinalizadas() {
		return this.repository.findAllByEstadoNotOrderByFechaSolicitudDesc(EstadoSolicitudAula.PENDIENTE);
	}
	
	@Override
	public void aprobarSolicitud(Long idSolicitud) throws BusinessException {
		SolicitudAula solicitud = this.repository.findOne(idSolicitud);
		AulaAlumno aa = new AulaAlumno();
		aa.setAlumno(solicitud.getAlumno());
		aa.setAula(solicitud.getAula());
		try {
		aulaAlumnoService.saveAlumno(aa);
		} catch(BusinessException be) {
			rechazarSolicitud(idSolicitud, be.getMessage());
			notificacionService.notificarRechazoUnionAula(solicitud.getAula(), solicitud.getAlumno(), be.getMessage());
			throw be;
		}
		solicitud.setEstado(EstadoSolicitudAula.APROBADA);
		solicitud.setFechaAccion(Calendar.getInstance().getTime());
		repository.save(solicitud);
	}

	@Override
	public void rechazarSolicitud(Long idSolicitud, String comentarios) throws BusinessException {
		SolicitudAula solicitud = this.repository.findOne(idSolicitud);
		solicitud.setEstado(EstadoSolicitudAula.RECHAZADA);
		solicitud.setFechaAccion(Calendar.getInstance().getTime());
		solicitud.setComentarios(comentarios);
		repository.save(solicitud);
	}
	
	@Resource(name = "notificacionService")
	public void setNotificacionService(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}
	
	@Resource(name = "solicitudAulaRepository")
	public void setRepository(SolicitudAulaRepository repository) {
		this.repository = repository;
	}
	
	@Resource(name = "alumnoService")
	public void setAlumnoService(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}
	
	@Resource(name = "aulaAlumnoService")
	public void setAulaAlumnoService(AulaAlumnoService aulaAlumnoService) {
		this.aulaAlumnoService = aulaAlumnoService;
	}
	
	@Resource(name = "aulaService")
	public void setAulaService(AulaService aulaService) {
		this.aulaService = aulaService;
	}
	
}

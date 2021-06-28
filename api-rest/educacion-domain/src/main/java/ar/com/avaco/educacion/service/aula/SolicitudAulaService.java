package ar.com.avaco.educacion.service.aula;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.SolicitudAula;

public interface SolicitudAulaService extends NJService<Long, SolicitudAula> {

	void notificarSolicitudUnion(Long idAula, Long idAlumno);

	void notificarRechazoSolicitudUnion(Long idAula, Long idAlumno, String motivo);

	void saveSolicitud(Long idAula, Long idAlumno) throws BusinessException;

	List<SolicitudAula> listSolicitudesPendientes();

	void aprobarSolicitud(Long idSolicitud) throws BusinessException;

	void notificarRechazoSolicitudUnion(Long idSolicitud, String motivo);

	void rechazarSolicitud(Long idSolicitud, String comentarios) throws BusinessException;

	List<SolicitudAula> listSolicitudesFinalizadas();

}

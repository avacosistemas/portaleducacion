package ar.com.avaco.educacion.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.SolicitudAula;
import ar.com.avaco.educacion.service.aula.SolicitudAulaService;
import ar.com.avaco.educacion.ws.dto.SolicitudAulaDTO;
import ar.com.avaco.educacion.ws.dto.SolicitudAulaFinalizadaDTO;
import ar.com.avaco.educacion.ws.service.SolicitudAulaEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("solicitudAulaEPService")
public class SolicitudAulaEPServiceImpl extends CRUDEPBaseService<Long, SolicitudAulaDTO, SolicitudAula, SolicitudAulaService> implements SolicitudAulaEPService {

	@Override
	public List<SolicitudAulaDTO> list() {
		List<SolicitudAula> solicitudes = this.service.listSolicitudesPendientes();
		List<SolicitudAulaDTO> dtos = new ArrayList<SolicitudAulaDTO>();
		solicitudes.forEach(x->dtos.add(convertToDto(x)));
		return dtos;
	}

	@Override
	public List<SolicitudAulaFinalizadaDTO> listFinalizadas() {
		List<SolicitudAula> solicitudes = this.service.listSolicitudesFinalizadas();
		List<SolicitudAulaFinalizadaDTO> dtos = new ArrayList<SolicitudAulaFinalizadaDTO>();
		solicitudes.forEach(x->dtos.add(new SolicitudAulaFinalizadaDTO(x)));
		return dtos;
	}
	
	@Override
	public void aprobar(Long id) throws BusinessException {
		this.service.aprobarSolicitud(id);
	}

	@Override
	public void rechazar(Long id, String comentario) throws BusinessException {
		this.service.rechazarSolicitud(id, comentario);
		this.service.notificarRechazoSolicitudUnion(id, comentario);
	}

	@Override
	protected SolicitudAula convertToEntity(SolicitudAulaDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SolicitudAulaDTO convertToDto(SolicitudAula solicitud) {
		SolicitudAulaDTO dto = new SolicitudAulaDTO();
		Alumno alumno = solicitud.getAlumno();
		Aula aula = solicitud.getAula();

		dto.setDocumentoAlumno(alumno.getIdentificacion().getTipo().toString() + "  " + alumno.getIdentificacion().getNumero());
		dto.setFechaHora(DateUtils.toString(aula.getDia()) + " - " + aula.getHora().toString() + " Hs.");
		dto.setId(solicitud.getId());
		dto.setIdAulaString(aula.getIdString());
		dto.setInstitucion(aula.getInstitucion() != null ? aula.getInstitucion().getNombre() : "");
		dto.setMateria(aula.getMateria().getDescripcion());
		dto.setNombreAlumno(alumno.getNombreApellido());
		dto.setNombreProfesor(aula.getProfesor().getNombreApellido());
		return dto;
	}

	@Override
	@Resource(name = "solicitudAulaService")
	protected void setService(SolicitudAulaService service) {
		this.service = service;
	}

}

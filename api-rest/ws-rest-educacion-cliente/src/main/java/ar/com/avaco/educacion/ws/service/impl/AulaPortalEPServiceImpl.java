package ar.com.avaco.educacion.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.service.aula.AulaService;
import ar.com.avaco.educacion.ws.dto.AulaAlumnoDTO;
import ar.com.avaco.educacion.ws.dto.AulaProfesorDTO;
import ar.com.avaco.educacion.ws.dto.URLAulaDTO;
import ar.com.avaco.educacion.ws.service.AulaPortalEPService;
import ar.com.avaco.ws.rest.security.util.ClienteUtils;

@Service("aulaPortalEPService")
public class AulaPortalEPServiceImpl implements AulaPortalEPService {

	private AulaService service;
	
	@Override
	public URLAulaDTO abrirClase(AulaProfesorDTO aulaProfesorDTO) throws BusinessException {
		Long idProfesor = ClienteUtils.getClienteLogueadoId();

		if (aulaProfesorDTO.getIdProfesor() == null)
			aulaProfesorDTO.setIdProfesor(idProfesor);

		// Y si es Admin?
		if (!idProfesor.equals(aulaProfesorDTO.getIdProfesor()))
			throw new BusinessException("No puede crear la clase de otro profesor");

		Aula aula = service.getAula(aulaProfesorDTO.getIdAula());
		String url = service.abrirClase(aula, aulaProfesorDTO.getIdProfesor());

		URLAulaDTO aulaDTO = new URLAulaDTO();
		aulaDTO.setUrlJoin(url);
		return aulaDTO;
	}

	@Override
	public URLAulaDTO unirseClase(AulaAlumnoDTO aulaAlumnoDTO) throws BusinessException {
		String idAlumno = ClienteUtils.getClienteLogueadoId().toString();

		aulaAlumnoDTO.setIdAlumno(idAlumno);
		Aula aula = service.getAula(Long.valueOf(aulaAlumnoDTO.getIdAula()));
		String url = service.unirseClase(aula, aulaAlumnoDTO.getIdAlumno());

		URLAulaDTO aulaDTO = new URLAulaDTO();
		aulaDTO.setUrlJoin(url);
		return aulaDTO;
	}

	@Resource(name = "aulaService")
	public void setService(AulaService service) {
		this.service = service;
	}
	
}

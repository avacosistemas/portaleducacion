package ar.com.avaco.educacion.ws.service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.AulaAlumnoDTO;
import ar.com.avaco.educacion.ws.dto.AulaProfesorDTO;
import ar.com.avaco.educacion.ws.dto.URLAulaDTO;

public interface AulaPortalEPService {

	URLAulaDTO abrirClase(AulaProfesorDTO aulaProfesorDTO) throws BusinessException;

	URLAulaDTO unirseClase(AulaAlumnoDTO aulaAlumnoDTO) throws BusinessException;

}

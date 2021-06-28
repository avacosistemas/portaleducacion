package ar.com.avaco.educacion.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.SolicitudAulaDTO;
import ar.com.avaco.educacion.ws.dto.SolicitudAulaFinalizadaDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface SolicitudAulaEPService extends CRUDEPService<Long, SolicitudAulaDTO> {

	void aprobar(Long id) throws BusinessException;

	void rechazar(Long id, String comentario) throws BusinessException;

	List<SolicitudAulaFinalizadaDTO> listFinalizadas();

}

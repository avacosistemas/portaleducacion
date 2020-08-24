package ar.com.avaco.educacion.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.AulaDTO;
import ar.com.avaco.educacion.ws.dto.AulaListadoDTO;
import ar.com.avaco.educacion.ws.dto.CompraAulaDTO;
import ar.com.avaco.educacion.ws.dto.EventoClaseDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface AulaEPService extends CRUDEPService<Long, AulaDTO> {

	AulaDTO getAula(Long id);
	
	AulaDTO crearAula(AulaDTO aulaDTO) throws BusinessException;
	
	List<AulaListadoDTO> listAulas();

	AulaDTO updateAula(Long id, AulaDTO aulaDTO) throws BusinessException;

	AulaDTO comprarClase(CompraAulaDTO compraAulaDTO) throws BusinessException;

	void registrarEventoClase(Long id, EventoClaseDTO eventoClaseDTO) throws BusinessException;

}

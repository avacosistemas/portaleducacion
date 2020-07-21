package ar.com.avaco.educacion.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.HorarioDisponibleDTO;
import ar.com.avaco.educacion.ws.dto.HorarioDisponibleFullDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface HorarioDisponibleEPService extends CRUDEPService<Long, HorarioDisponibleDTO> {

	List<HorarioDisponibleDTO> listHorariosDispProfesor(Long idProfesor);
	
	List<HorarioDisponibleDTO> addHorarioDisponible(HorarioDisponibleFullDTO horariosDispDto) throws BusinessException;

}

package ar.com.avaco.educacion.service.disponibilidad;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;

public interface HorarioDisponibleService extends NJService<Long, HorarioDisponible> {
	
	/**
	 * Obtiene lista de los horarios disponibles de un profesor
	 * 
	 * @param idProfesor id del profesor
	 * @return lista de horarios disponibles
	 */
	List<HorarioDisponible> listHorariosDispProfesor(Long idProfesor);
	
	/**
	 * Agrega horarios disponibles para un profesor
	 * 
	 * @param entities lista de horarios a agregar
	 * @param idProfesor id del profesor
	 * @return lista de horarios disponibles agregados
	 * @throws BusinessException error de negocio
	 */
	List<HorarioDisponible> addHorariosDisponibles(List<HorarioDisponible> entities, Long idProfesor) throws BusinessException;
	
}

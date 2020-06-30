package ar.com.avaco.educacion.service.disponibilidad;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;

public interface HorarioDisponibleService extends NJService<Long, HorarioDisponible> {
	
	public List<HorarioDisponible> listHorariosDispProfesor(Long idProfesor);
	
}

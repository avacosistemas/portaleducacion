package ar.com.avaco.educacion.service.disponibilidad;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;
import ar.com.avaco.educacion.repository.disponibilidad.HorarioDisponibleRepository;


@Transactional
@Service("horarioDisponibleService")
public class HorarioDisponibleServiceImpl extends NJBaseService<Long, HorarioDisponible, HorarioDisponibleRepository> implements HorarioDisponibleService {

	
	@Override
	public List<HorarioDisponible> listHorariosDispProfesor(Long idProfesor) {
		return getRepository().findAllByProfesorId(idProfesor);
	}
	
	@Resource(name = "horarioDisponibleRepository")
	void setMateriaRepository(HorarioDisponibleRepository disponibilidadRepository) {
		this.repository = disponibilidadRepository;
	}
	
}

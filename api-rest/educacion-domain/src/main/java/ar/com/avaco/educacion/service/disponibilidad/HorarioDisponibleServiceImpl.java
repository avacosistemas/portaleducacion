package ar.com.avaco.educacion.service.disponibilidad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.repository.disponibilidad.HorarioDisponibleRepository;
import ar.com.avaco.educacion.service.profesor.ProfesorService;

@Transactional
@Service("horarioDisponibleService")
public class HorarioDisponibleServiceImpl extends NJBaseService<Long, HorarioDisponible, HorarioDisponibleRepository> implements HorarioDisponibleService {


	@Autowired
	private ProfesorService profesorService;

	@Override
	public List<HorarioDisponible> listHorariosDispProfesor(Long idProfesor) {
		return getRepository().findAllByProfesorIdOrderByDiaAscHoraAsc(idProfesor);
	}
	
	/**
	 * @see HorarioDisponibleService#addHorariosDisponibles(List, Long)
	 */
	@Override
	public List<HorarioDisponible> addHorariosDisponibles(List<HorarioDisponible> entities, Long idProfesor) throws BusinessException {

		List<HorarioDisponible> entitiesForSave = new ArrayList<>();
		Profesor profesor = profesorService.get(idProfesor);

		for(HorarioDisponible entity: entities) {
			entity.setProfesor(profesor);
			
			if(!this.existeDuplicado(entity)) {
				entitiesForSave.add(entity);
			}
		}

		List<HorarioDisponible> horariosDisponibles = this.getRepository().save(entitiesForSave);

		return horariosDisponibles;
	}

	/**
	 * Valida que no exista un dia y horario para un profesor
	 * @param horarioDisp horario disponible
	 */
	public boolean existeDuplicado(HorarioDisponible horarioDisp) {
	
		
		Map<String, String> errores = new HashMap<String, String>();

		if (getRepository().findByDiaAndHoraAndProfesorId(horarioDisp.getDia(), horarioDisp.getHora(), horarioDisp.getProfesor().getId()) != null) {
			errores.put("horaDesde", "Hay horarios disponibles para el profesor que ya se encuentran registrados");
		}

		if (!errores.isEmpty()) {
			return true;
			//throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}
		
		return false;
	}
	
	@Resource(name = "horarioDisponibleRepository")
	void setMateriaRepository(HorarioDisponibleRepository disponibilidadRepository) {
		this.repository = disponibilidadRepository;
	}

}

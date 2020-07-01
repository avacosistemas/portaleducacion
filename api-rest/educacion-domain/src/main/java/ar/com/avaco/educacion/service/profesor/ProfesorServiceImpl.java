package ar.com.avaco.educacion.service.profesor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.repository.profesor.ProfesorRepository;
import ar.com.avaco.educacion.service.materia.MateriaService;

@Transactional
@Service("profesorService")
public class ProfesorServiceImpl extends NJBaseService<Long, Profesor, ProfesorRepository> implements ProfesorService {

	private MateriaService materiaService;
	
	@Resource(name = "profesorRepository")
	void setProfesorRepository(ProfesorRepository profesorRepository) {
		this.repository = profesorRepository;
	}
	
	@Override
	public Profesor getProfesor(Long id) {
		return getRepository().getProfesor(id);
	}
	
	@Override
	public Profesor getMateriaProfesor(Long id) {
		List<Profesor> profes = getRepository().selectMateriasProfesor(id);
		return profes.get(0);
	}
	
	@Override
	public List<Profesor> listProfesores() {
		return getRepository().listProfesores();
	}
	
	@Override
	public Profesor createMateriaProfesor(Long idMateria, Long idProfesor) throws BusinessException {

		Profesor profesor = this.getRepository().getOne(idProfesor);
		Materia materia = materiaService.get(idProfesor);
		materia.getProfesores().add(profesor);
	
		profesor = this.getRepository().save(profesor);

		return profesor;
	}
	

	@Override
	public Profesor bloquearHabilitarProfesor(Profesor entity, boolean bloquear) throws BusinessException {

		Profesor profesor = this.get(entity.getId());
		profesor.setId(entity.getId());
		profesor.setBloqueado(bloquear);
		
		profesor = this.getRepository().save(profesor);

		return profesor;
	}
	
	@Resource(name = "materiaService")
	public void setMateriaService(MateriaService materiaService) {
		this.materiaService = materiaService;
	}


}

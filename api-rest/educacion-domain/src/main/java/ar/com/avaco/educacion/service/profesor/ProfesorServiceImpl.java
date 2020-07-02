package ar.com.avaco.educacion.service.profesor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.repository.profesor.ProfesorRepository;
import ar.com.avaco.educacion.service.cliente.ClienteService;
import ar.com.avaco.educacion.service.materia.MateriaService;

@Transactional
@Service("profesorService")
public class ProfesorServiceImpl extends NJBaseService<Long, Profesor, ProfesorRepository> implements ProfesorService {

	
	private MateriaService materiaService;
	private ClienteService clienteService;
	
	@Autowired
	public ProfesorServiceImpl(MateriaService materiaService, ClienteService clienteService) {
		this.materiaService = materiaService;
		this.clienteService = clienteService;
	}

	/**
	 * @see ProfesorService#getProfesor(Long)
	 */
	@Override
	public Profesor getProfesor(Long id) {
		return getRepository().getProfesor(id);
	}
	
	/**
	 * @see ProfesorService#getMateriaProfesor(Long)
	 */
	@Override
	public Profesor getMateriaProfesor(Long id) {
		List<Profesor> profes = getRepository().selectMateriasProfesor(id);
		return profes.get(0);
	}
	
	/**
	 * @see ProfesorService#listProfesores()
	 */
	@Override
	public List<Profesor> listProfesores() {
		return getRepository().listProfesores();
	}
	
	/**
	 * @see ProfesorService#createMateriaProfesor(Long, Long)
	 */
	@Override
	public Profesor createMateriaProfesor(Long idMateria, Long idProfesor) throws BusinessException {

		Profesor profesor = this.getRepository().getOne(idProfesor);
		Materia materia = materiaService.get(idMateria);
		
		if(!profesor.getMaterias().contains(materia)) {
			
			profesor.addMateria(materia);
			materia.addProfesor(profesor);

			profesor = this.getRepository().save(profesor);

		} else {
			throw new BusinessException("El profesor ya tiene asociada la materia");
		}
		
		return profesor;
	}
	
	/**
	 * @see ProfesorService#removeMateriaProfesor(Long, Long)
	 */
	@Override
	public void removeMateriaProfesor(Long idMateria, Long idProfesor) throws BusinessException {

		Profesor profesor = this.getRepository().getOne(idProfesor);
		Materia materia = materiaService.get(idMateria);
		profesor.removeMateria(materia);
		materia.removeProfesor(profesor);
		
		profesor = this.getRepository().save(profesor);

	}
	
	/**
	 * @see ProfesorService#createProfesor(Profesor)
	 */
	@Override
	public Profesor createProfesor(Profesor profesor) throws BusinessException {
		Profesor newProfesor = (Profesor) clienteService.registrarClientePersona(profesor);
		return newProfesor;
	}
	
	/**
	 * @see ProfesorService#updateProfesor(Profesor)
	 */
	@Override
	public Profesor updateProfesor(Profesor entity) throws BusinessException {
		Profesor profesor = (Profesor) clienteService.updateProfesorAlumno(entity);
		return profesor;
	}

	/**
	 * @see ProfesorService#bloquearHabilitarProfesor(Profesor, boolean)
	 */
	@Override
	public Profesor bloquearHabilitarProfesor(Profesor entity, boolean bloquear) throws BusinessException {
		Profesor profesor = this.get(entity.getId());
		profesor.setId(entity.getId());
		profesor.setBloqueado(bloquear);
		
		profesor = this.getRepository().save(profesor);

		return profesor;
	}

	@Resource(name = "profesorRepository")
	void setProfesorRepository(ProfesorRepository profesorRepository) {
		this.repository = profesorRepository;
	}
	
}

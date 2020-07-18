package ar.com.avaco.educacion.service.aula;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.repository.aula.AulaRepository;
import ar.com.avaco.educacion.service.alumno.AlumnoService;
import ar.com.avaco.educacion.service.materia.MateriaService;
import ar.com.avaco.educacion.service.profesor.ProfesorService;

@Transactional
@Service("aulaService")
public class AulaServiceImpl extends NJBaseService<Long, Aula, AulaRepository> implements AulaService {

	private MateriaService materiaService;
	
	private ProfesorService profesorService;
	
	private AlumnoService alumnoService;
	
	@Autowired
	public AulaServiceImpl(MateriaService materiaService, ProfesorService profesorService, AlumnoService alumnoService) {
		this.materiaService = materiaService;
		this.profesorService = profesorService;
		this.alumnoService = alumnoService;
	}
	
	@Resource(name = "aulaRepository")
	void setAulaRepository(AulaRepository aulaRepository) {
		this.repository = aulaRepository;
	}
		
	@Override
	public Aula getAula(Long id) {
		return getRepository().getAula(id);
	}

	@Override
	public List<Aula> getAulas() {
		return getRepository().listAulas();
	}

	@Override
	public Aula crearAula(Aula aula) throws BusinessException {
		validateAulaNoEmpty(aula);
		return getRepository().save(aula);
	}

	
	@Override
	public Aula addProfesorAula(Long idAula, Long idProfesor) throws BusinessException {
		Aula aula = this.getRepository().getOne(idAula);
		Profesor profesor = profesorService.getProfesor(idProfesor);
		
		if (profesor==null)
			throw new BusinessException("El profesor id "+idProfesor+" no existe");
			
		if(!aula.getProfesores().contains(profesor)) {
			
			aula.addProfesor(profesor);
			profesor.addAula(aula);
			
			aula = this.getRepository().save(aula);

		} else {
			throw new BusinessException("El aula ya tiene asociado al profesor");
		}
		
		return aula;
	}

	@Override
	public void removeAulaProfesor(Long idAula, Long idProfesor) throws BusinessException {
		Aula aula = this.getRepository().getOne(idAula);
		Profesor profesor = profesorService.getProfesor(idProfesor);

		aula.removeProfesor(profesor);
		profesor.removeAula(aula);
				
		aula = this.getRepository().save(aula);		
	}
	

	
	@Override
	public Aula addAlumnoAula(Long idAula, Long idAlumno) throws BusinessException {
		Aula aula = this.getRepository().getOne(idAula);
		Alumno alumno = alumnoService.get(idAlumno);
		
		if (alumno==null)
			throw new BusinessException("El alumno id "+idAlumno+" no existe");
		
		if (aula.getInstitucion()!=null && !alumno.getInstituciones().contains(aula.getInstitucion())) {
			throw new BusinessException("El alumno no pertenece a la Institucion del aula");
		}
					
		if(!aula.getAlumnos().contains(alumno)) {
			
			aula.addAlumno(alumno);
			alumno.addAula(aula);
			
			aula = this.getRepository().save(aula);

		} else {
			throw new BusinessException("El aula ya tiene asociado al alumno");
		}
		
		return aula;
	}

	@Override
	public void removeAulaAlumno(Long idAula, Long idAlumno) {
		Aula aula = this.getRepository().getOne(idAula);
		Alumno alumno= alumnoService.get(idAlumno);

		aula.removeAlumno(alumno);
		alumno.removeAula(aula);
				
		aula = this.getRepository().save(aula);		
		
	}

	@Override
	public Aula updateAula(Aula aula) throws BusinessException {
		validateAulaNoEmpty(aula);
		
		Aula entity = this.get(aula.getId());	
		if (entity==null)
			throw new BusinessException("Aula no existe.");
		
		entity.setDia(aula.getDia());
		entity.setHora(aula.getHora());
		entity.setMateria(materiaService.get(aula.getMateria().getId()));
		entity=getRepository().save(entity);
				
		return getAula(aula.getId());
	}

	
	/**
	 * Valida que el aula tenga los campos requeridos
	 * 
	 * @param aula
	 * @throws BusinessException
	 */
	private void validateAulaNoEmpty(Aula aula) throws BusinessException {
		if (aula == null) {
			throw new BusinessException("Aula vacía.");
		} else if (aula.getMateria().getId() == null) {
			throw new BusinessException("Materia vacío.");
		} else if (aula.getHora() == null) {
			throw new BusinessException("Hora vacío.");
		} else if (aula.getDia() == null) {
			throw new BusinessException("Dia vacío.");
		}
	}
	
	
}

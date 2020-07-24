package ar.com.avaco.educacion.service.aula;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.HorasAlumno;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.repository.aula.AulaRepository;
import ar.com.avaco.educacion.service.alumno.AlumnoService;
import ar.com.avaco.educacion.service.decidir.DecidirService;
import ar.com.avaco.educacion.service.horas.disp.HorasAlumnoService;
import ar.com.avaco.educacion.service.materia.MateriaService;
import ar.com.avaco.educacion.service.profesor.ProfesorService;

@Transactional
@Service("aulaService")
public class AulaServiceImpl extends NJBaseService<Long, Aula, AulaRepository> implements AulaService {

	private MateriaService materiaService;
	
	private ProfesorService profesorService;
	
	private AlumnoService alumnoService;
	
	private DecidirService decidirService;
	
	private HorasAlumnoService horasAlumnoService;  
	
	@Autowired
	public AulaServiceImpl(MateriaService materiaService, ProfesorService profesorService, AlumnoService alumnoService, DecidirService decidirService, HorasAlumnoService horasAlumnoService) {
		this.materiaService = materiaService;
		this.profesorService = profesorService;
		this.alumnoService = alumnoService;
		this.decidirService = decidirService;
		this.horasAlumnoService = horasAlumnoService;
		
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
			
		if(aula.getProfesores()!=null && aula.getProfesores().contains(profesor)) 			
			throw new BusinessException("El aula ya tiene asociado al profesor");

		if (profesor.getAulas()!=null && !profesor.getAulas().isEmpty()) {
			for (Aula aulaProfesor : profesor.getAulas()) {
				if (seSolapanAulas(aulaProfesor, aula, 60)) {
					throw new BusinessException("El profesor ya tiene una clase en ese dia y horario. Ver Aula para Materia"+aulaProfesor.getMateria().getDescripcion());
				}			
			}
		}
			
			
		aula.addProfesor(profesor);
		profesor.addAula(aula);
			
		aula = this.getRepository().save(aula);
		
		return aula;
	}

	@Override
	public void removeAulaProfesor(Long idAula, Long idProfesor) throws BusinessException {
		Aula aula = this.getRepository().getAula(idAula);
		Profesor profesor = profesorService.getProfesor(idProfesor);

		aula.removeProfesor(profesor);
		profesor.removeAula(aula);
				
		aula = this.getRepository().save(aula);		
	}
	

	
	@Override
	public Aula addAlumnoAula(Long idAula, Long idAlumno) throws BusinessException {
		Aula aula = this.getRepository().getAula(idAula);
		Alumno alumno = alumnoService.get(idAlumno);
		
		if (alumno==null)
			throw new BusinessException("El alumno id "+idAlumno+" no existe");
		
		if (aula.getInstitucion()!=null && !alumno.getInstituciones().contains(aula.getInstitucion())) 
			throw new BusinessException("El alumno no pertenece a la Institucion del aula");
		
		
		if(aula.getAlumnos()!=null && aula.getAlumnos().contains(alumno)) 
			throw new BusinessException("El aula ya tiene asociado al alumno");
		
		if (alumno.getAulas()!=null && !alumno.getAulas().isEmpty()) {
			for (Aula aulaAlumno : alumno.getAulas()) {
				if (seSolapanAulas(aulaAlumno, aula, 60)) {
					throw new BusinessException("El alumno ya tiene una clase en ese dia y horario. Ver Aula para Materia: "+aulaAlumno.getMateria().getDescripcion());
				}			
			}
		}
			
		aula.addAlumno(alumno);
		alumno.addAula(aula);
		
		aula = this.getRepository().save(aula);	
		
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
		} else if (aula.getMateria()==null || aula.getMateria().getId() == null) {
			throw new BusinessException("Materia vacío.");
		} else if (aula.getHora() == null) {
			throw new BusinessException("Hora vacío.");
		} else if (aula.getDia() == null) {
			throw new BusinessException("Dia vacío.");
		}
	}

	@Override
	public Aula comprarClase(Long idAlumno, Long idProfesor, Long idMateria, Date dia, String hora)
			throws BusinessException {
					
		//TODO Validar el pago por decidir. if (decidir)
		
		Profesor profesor=profesorService.getProfesor(idProfesor);
		if (profesor==null)
			throw new BusinessException("Profesor no existe");
		
		Alumno alumno=alumnoService.getAlumno(idAlumno);
		if (alumno==null)
			throw new BusinessException("Alumno no existe");
		
		Materia materia=materiaService.get(idMateria);
		if (materia==null)
			throw new BusinessException("Materia no existe");
		
		//Agregar horas disponibles
		HorasAlumno horasAlumno=horasAlumnoService.getByAlumnoYProfesorId(idAlumno, idProfesor);
		if (horasAlumno==null) { 
			horasAlumno = new HorasAlumno();		
			horasAlumno.setAlumno(alumno);
			horasAlumno.setProfesor(profesor);		
			horasAlumno.setHorasDisponibles(0);
		}
		
		horasAlumno.setHorasDisponibles(horasAlumno.getHorasDisponibles()+1);
		horasAlumnoService.save(horasAlumno);
		///////////////////////////
				
		//Crear aula				
		Aula aula=new Aula();
		aula.setDia(dia);
		aula.setHora(Integer.parseInt(hora));
		aula.setMateria(materia);
					
		aula=this.crearAula(aula);
		this.addAlumnoAula(aula.getId(), idAlumno);
		this.addProfesorAula(aula.getId(), idProfesor);		
		////////////////////
		
		//Descontar horas disponibles
		horasAlumno.setHorasDisponibles(horasAlumno.getHorasDisponibles()-1);
		horasAlumnoService.save(horasAlumno);
		////////////////////
		
		return aula;
	}
	

	/**
	 * Compara dos aulas si su dia y hora se solapan
	 * 
	 * @param aula1 Aula 1
	 * @param aula2 Aula 2
	 * @param duracionClase en Minutos
	 * @return
	 */
	public boolean seSolapanAulas(Aula aula1, Aula aula2, int duracionClase) {
		return seSolapan(aula1.getDia(), aula2.getDia(), aula1.getHora(), aula2.getHora(), duracionClase);
	}
	
	public boolean seSolapan(Date dia1, Date dia2, Integer hora1, Integer hora2, int duracionClase) {
	
		if (dia1 == null|| dia2 == null|| hora1 == null|| hora2 == null)
			return false;
		
		//Si es el mismo dia		
		if (dia1.toString().equals(dia2.toString())) {
			//Si la hora1 esta entre 
			LocalTime aula1LT = LocalTime.parse(hora1.toString());
			LocalTime aula2LT = LocalTime.parse(hora2.toString());
			long minutos=Math.abs(ChronoUnit.MINUTES.between(aula1LT, aula2LT));
			if (minutos < duracionClase)
				return true;
		}
		
		return false;		
	}
	
	
}

package ar.com.avaco.educacion.service.aula;

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
import ar.com.avaco.educacion.domain.entities.AulaAlumno;
import ar.com.avaco.educacion.domain.entities.HorasAlumno;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.aulaVirtual.Clase;
import ar.com.avaco.educacion.exception.AulaVirtualException;
import ar.com.avaco.educacion.repository.aula.AulaRepository;
import ar.com.avaco.educacion.service.SolapaUtils;
import ar.com.avaco.educacion.service.alumno.AlumnoService;
import ar.com.avaco.educacion.service.aulaVirtual.AulaVirtualService;
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
	
	private AulaVirtualService aulaVirtualService; 
	
	private AulaAlumnoService aulaAlumnoService;
	
	@Autowired
	public AulaServiceImpl(MateriaService materiaService, ProfesorService profesorService, AlumnoService alumnoService, DecidirService decidirService, HorasAlumnoService horasAlumnoService, AulaVirtualService aulaVirtualService) {
		this.materiaService = materiaService;
		this.profesorService = profesorService;
		this.alumnoService = alumnoService;
		this.decidirService = decidirService;
		this.horasAlumnoService = horasAlumnoService;
		this.aulaVirtualService = aulaVirtualService;
		
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
				if (SolapaUtils.seSolapanAulas(aulaProfesor, aula, 60)) {
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
	
//	@Override
//	public void removeAulaAlumno(Long idAula, Long idAlumno) {
//		Aula aula = this.getRepository().getOne(idAula);
//		Alumno alumno= alumnoService.get(idAlumno);
//
//		aula.removeAlumno(alumno);
//		alumno.removeAula(aula);
//				
//		aula = this.getRepository().save(aula);		
//		
//	}

	@Override
	public Aula updateAula(Aula aula) throws BusinessException {
		validateAulaNoEmpty(aula);
		
		Aula entity = this.get(aula.getId());	
		if (entity==null)
			throw new BusinessException("Aula no existe.");
		
		entity.setDia(aula.getDia());
		entity.setHora(aula.getHora());
		entity.setMateria(materiaService.get(aula.getMateria().getId()));
		entity.setProfesor(aula.getProfesor());
		entity.setInstitucion(aula.getInstitucion());
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
					
		aula = this.crearAula(aula);
		
		this.addProfesorAula(aula.getId(), idProfesor);		
		this.addAlumnoAula(aula, alumno);
		////////////////////
		
		//Descontar horas disponibles
		horasAlumno.setHorasDisponibles(horasAlumno.getHorasDisponibles()-1);
		horasAlumnoService.save(horasAlumno);
		////////////////////
		
		return aula;
	}
	
	private void addAlumnoAula(Aula aula, Alumno alumno) {
		AulaAlumno entity = new AulaAlumno();
		entity.setAula(aula);
		entity.setAlumno(alumno);
		this.aulaAlumnoService.save(entity);
	}

	@Resource(name = "aulaAlumnoService")
	public void setAulaAlumnoService(AulaAlumnoService aulaAlumnoService) {
		this.aulaAlumnoService = aulaAlumnoService;
	}
	

	@Override
	public String abrirClase(Aula aula, Long idProfesor) throws BusinessException {
		Profesor profesor = profesorService.getProfesor(idProfesor);
		
		if (profesor==null) {
			throw new BusinessException("Profesor no existe");			
		}
		
		if (aula==null) {
			throw new BusinessException("Aula no existe");			
		}
		
		try {
			Clase clase=aulaVirtualService.crearClase(profesor, aula);
			return clase.getUrl();
		} catch (AulaVirtualException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void validarEventoClase(String fromIP) throws BusinessException {
		try {
			aulaVirtualService.validarOrigenEvento(fromIP);
		} catch (AulaVirtualException e) {
			throw new BusinessException(e.getMessage());
		}		
	}

	@Override
	public String unirseClase(Aula aula, String idAlumno) throws BusinessException {
		Alumno alumno = alumnoService.getAlumno(Long.valueOf(idAlumno));
		
		if (alumno==null) {
			throw new BusinessException("Alumno no existe");			
		}
		
		if (aula==null) {
			throw new BusinessException("Aula no existe");			
		}
		
		try {
			String url=aulaVirtualService.unirseAlumnoClase(aula.generatedIdAula(), alumno);
			return url;
		} catch (AulaVirtualException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	
}

package ar.com.avaco.educacion.service.catalogo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.repository.aula.AulaRepository;
import ar.com.avaco.educacion.repository.disponibilidad.HorarioDisponibleRepository;
import ar.com.avaco.educacion.repository.pregresp.PreguntaRespuestaRepository;
import ar.com.avaco.educacion.repository.profesor.ProfesorRepository;

@Transactional
@Service("catalogoService")
public class CatalogoServiceImpl implements CatalogoService {

	private ProfesorRepository profesorRepo;
	private HorarioDisponibleRepository horariosRepo;
	private PreguntaRespuestaRepository pregutaRtaRepo;
	private AulaRepository aulaRepo;
	
	@Autowired
	public CatalogoServiceImpl(
			ProfesorRepository profesorRepo, 
			HorarioDisponibleRepository horariosRepo,
			PreguntaRespuestaRepository pregutaRtaRepo,
			AulaRepository aulaRepo) {

		this.profesorRepo = profesorRepo;
		this.horariosRepo = horariosRepo;
		this.pregutaRtaRepo = pregutaRtaRepo;
		this.aulaRepo = aulaRepo;
	}

	@Override
	public List<Profesor> listCatalogoProfesor(String campo, boolean desc, Long idMateria, Integer idNivel) {
		List<Profesor> catalogosProfesores = profesorRepo.listCatalogoDocente(campo, desc);
		
		//TODO Filtrar materia o nivel
		
		return catalogosProfesores;
	}
	
	@Override
	public Profesor getCatalogoProfesor(Long idProfesor) {
		Profesor profesor = profesorRepo.findProfesorConMaterias(idProfesor);
		return profesor;
	}
	
	@Override
	public double getCatalogoCalificacion(Long idProfesor) {
		
		List<Aula> aulasByProfesor = aulaRepo.findAllByProfesoresIdIn(idProfesor);
		
		Integer calificacionTotal = aulasByProfesor.stream().map(aula->aula.getCalificacion()).collect(Collectors.summingInt(Integer::intValue));

		double promedioCalificacion = (double) (calificacionTotal / aulasByProfesor.size());
		
		return promedioCalificacion;
	}
	
	
	@Override
	public List<HorarioDisponible> getCatalogoHorarios(LocalDate fecha, Long idProfesor) {
	
		List<HorarioDisponible> horariosDisponibles = horariosRepo.selectHorariosByDiaAndProfesor(fecha.getDayOfWeek(), idProfesor);
		List<LocalTime> horariosAula = aulaRepo.selectHorariosByFechaAndProfesor(fecha, idProfesor);
		
		horariosDisponibles.stream().map(hd -> hd.getHora()).collect(Collectors.toList()).removeAll(horariosAula);
	
		return horariosDisponibles;
	}
	
	@Override
	public List<PreguntaRespuesta> getCatalogoConsulta(Long idProfesor) {
		return pregutaRtaRepo.selectAllByProfesorId(idProfesor);
	}
	
	
	@Override
	public PreguntaRespuesta createCatalogoConsulta(PreguntaRespuesta entity) throws BusinessException {
		
		entity = pregutaRtaRepo.save(entity);

		return entity;
	}

}

package ar.com.avaco.educacion.service.catalogo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.OrdenCatalogo;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.repository.aula.AulaRepository;
import ar.com.avaco.educacion.repository.disponibilidad.HorarioDisponibleRepository;
import ar.com.avaco.educacion.repository.pregresp.PreguntaRespuestaRepository;
import ar.com.avaco.educacion.repository.profesor.ProfesorRepository;
import ar.com.avaco.utils.DateUtils;

@Transactional
@Service("catalogoService")
public class CatalogoServiceImpl implements CatalogoService {

	private ProfesorRepository profesorRepo;
	private HorarioDisponibleRepository horariosRepo;
	private PreguntaRespuestaRepository preguntaRtaRepo;
	private AulaRepository aulaRepo;
	
	@Autowired
	public CatalogoServiceImpl(
			ProfesorRepository profesorRepo, 
			HorarioDisponibleRepository horariosRepo,
			PreguntaRespuestaRepository preguntaRtaRepo,
			AulaRepository aulaRepo) {

		this.profesorRepo = profesorRepo;
		this.horariosRepo = horariosRepo;
		this.preguntaRtaRepo = preguntaRtaRepo;
		this.aulaRepo = aulaRepo;
	}

	@Override
	public List<Profesor> listCatalogoProfesor(OrdenCatalogo filtro, Long idMateria, Integer idNivel) {
		List<Profesor> catalogosProfesores;
		Sort orden = new Sort(Sort.Direction.DESC, "calificacion");
		
		if(filtro.equals(OrdenCatalogo.MAYOR_PRECIO)){
			orden = new Sort(Sort.Direction.DESC, "valorHora");
		} else if(filtro.equals(OrdenCatalogo.MAYOR_PRECIO)){
			orden = new Sort(Sort.Direction.ASC, "valorHora");
		}
	
		if(idMateria!=null) {
			catalogosProfesores = profesorRepo.findAllCatalogoDocenteByMateria(idMateria, orden);
		} else if(idNivel!=null) {
			catalogosProfesores = profesorRepo.findAllCatalogoDocenteByNivel(idNivel, orden);
		} else {
			catalogosProfesores = profesorRepo.findAllProfesoresOrder(orden);
		}
		
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
		
		Double calificacionTotal = aulasByProfesor.stream().map(aula->aula.getCalificacion()).collect(Collectors.summingDouble(Double::doubleValue));

		double promedioCalificacion = (double) (calificacionTotal / aulasByProfesor.size());
		
		return promedioCalificacion;
	}
	
	
	@Override
	public List<HorarioDisponible> getCatalogoHorarios(LocalDate fecha, Long idProfesor) {
		List<HorarioDisponible> horariosDisponibles = horariosRepo.selectHorariosByDiaAndProfesor(fecha.getDayOfWeek(), idProfesor);
		List<LocalTime> horariosAula = aulaRepo.selectHorariosByFechaAndProfesor(DateUtils.toDate(fecha), idProfesor);
		horariosDisponibles.stream().map(hd -> hd.getHora()).collect(Collectors.toList()).removeAll(horariosAula);
		return horariosDisponibles;
	}
	
	@Override
	public List<PreguntaRespuesta> getCatalogoConsulta(Long idProfesor) {
		return preguntaRtaRepo.selectAllByProfesorId(idProfesor);
	}
	
	
	@Override
	public PreguntaRespuesta createCatalogoConsulta(PreguntaRespuesta entity) throws BusinessException {
		
		Profesor profesor = profesorRepo.findOne(entity.getProfesor().getId());
		entity.setProfesor(profesor);
		entity.setFechaPregunta(DateUtils.getFechaYHoraActual());
		entity = preguntaRtaRepo.save(entity);

		return entity;
	}

}

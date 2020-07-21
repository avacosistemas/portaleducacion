package ar.com.avaco.educacion.service.catalogo;


import java.time.LocalDate;
import java.util.List;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.domain.entities.Profesor;

public interface CatalogoService {

	Profesor getCatalogoProfesor(Long id);

	List<PreguntaRespuesta> getCatalogoConsulta(Long idProfesor);

	List<HorarioDisponible> getCatalogoHorarios(LocalDate fecha, Long idProfesor);

	double getCatalogoCalificacion(Long idProfesor);

	PreguntaRespuesta createCatalogoConsulta(PreguntaRespuesta entity) throws BusinessException;

	List<Profesor> listCatalogoProfesor(String campo, boolean desc, Long idMateria, Integer idNivel);
	
}

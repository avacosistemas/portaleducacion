package ar.com.avaco.educacion.ws.service;

import java.time.LocalDate;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.CatalogoProfesorDTO;
import ar.com.avaco.educacion.ws.dto.ConsultaDTO;
import ar.com.avaco.educacion.ws.dto.HorarioDisponibleDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaRespuestaDTO;

public interface CatalogoProfesorEPService {

	CatalogoProfesorDTO getCatalogoProfesor(Long id);

	double getCatalogoCalificacion(Long id);

	List<HorarioDisponibleDTO> getCatalogoHorarios(LocalDate fecha, Long id);

	List<PreguntaRespuestaDTO> getCatalogoConsulta(Long id);

	ConsultaDTO createCatalogoConsulta(ConsultaDTO consultaDto) throws BusinessException;

	List<CatalogoProfesorDTO> listCatalogoProfesor(String campo, boolean desc, Long idMateria, Integer idNivel);

}
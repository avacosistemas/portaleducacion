package ar.com.avaco.educacion.service.alumno;


import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;

public interface AlumnoService extends NJService<Long, Alumno> {

	/**
	 * Recupera un alumno por su id
	 * 
	 * @param id Id del alumno
	 * @return alumno
	 */
	Alumno getAlumno(Long id);
	
	/**
	 * Obtiene los alumnos
	 * 
	 * @return lista de alumnos
	 */
	List<Alumno> listAlumnos();
	
	/**
	 * Actualiza un alumno
	 * 
	 * @param entity Alumno a grabar
	 * @return alumno actualizado
	 * @throws BusinessException error de negocio
	 */
	Alumno updateAlumno(Alumno entity) throws BusinessException;

	/**
	 * Crea un nuevo alumno
	 * 
	 * @param alumno Alumno a grabar
	 * @return alumno creado
	 * @throws BusinessException error de negocio
	 */
	Alumno createAlumno(Alumno alumno) throws BusinessException;
	
	/**
	 * Bloquea y Habilita un alumno
	 * 
	 * @param entity Alumno a bloquear/habilitar
	 * @param bloquear True para bloquear, sino habilita
	 * @return alumno bloqueado/habilitado
	 * @throws BusinessException error de negocio
	 */
	Alumno bloquearHabilitarAlumno(Alumno entity, boolean bloquear) throws BusinessException;

	/**
	 * Registrar un nuevo alumno
	 * - Este metodo es usado para el registro del alumno POR el Alumno. A diferncia del crear alumno donde el alumno es creado por el Administrador.
	 * 
	 * @param alumno
	 * 
	 * @return
	 * @throws BusinessException error de negocio
	 */
	Alumno registrarAlumno(Alumno alumno) throws BusinessException;

	List<Alumno> listAlumnosByInstitucion(Long idInstitucion);

	
}

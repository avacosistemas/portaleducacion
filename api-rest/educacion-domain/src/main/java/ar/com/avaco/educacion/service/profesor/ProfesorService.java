package ar.com.avaco.educacion.service.profesor;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Profesor;

public interface ProfesorService extends NJService<Long, Profesor> {
	
	/**
	 * Recupera un profesor por su id
	 * 
	 * @param id Id del profesor
	 * @return profesor
	 */
	Profesor getProfesor(Long id);
	
	/**
	 * Recupera un profesor y sus materias por su id
	 * 
	 * @param id Id del profesor
	 * @return profesor
	 */
	Profesor getMateriaProfesor(Long id);
	
	/**
	 * Obtiene los profesores
	 * 
	 * @return lista de profesores
	 */
	List<Profesor> listProfesores();
	
	/**
	 * Crea registo profesor-materia
	 * 
	 * @param idMateria Id de la materia
	 * @param idProfesor Id del profesor
	 * @return profesor
	 * @throws BusinessException error de negocio
	 */
	Profesor createMateriaProfesor(Long idMateria, Long idProfesor) throws BusinessException;
	
	/**
	 * Actualiza un profesor
	 * 
	 * @param entity Profesor a grabar
	 * @return profesor actualizado
	 * @throws BusinessException error de negocio
	 */
	Profesor updateProfesor(Profesor entity) throws BusinessException;

	/**
	 * Crea un nuevo profesor
	 * 
	 * @param profesor Profesor a grabar
	 * @return profesor creado
	 * @throws BusinessException error de negocio
	 */
	Profesor createProfesor(Profesor profesor) throws BusinessException;
	
	/**
	 * Bloquea y Habilita un profesor
	 * 
	 * @param entity Profesor a bloquear/habilitar
	 * @param bloquear True para bloquear, sino habilita
	 * @return profesor bloqueado/habilitado
	 * @throws BusinessException error de negocio
	 */
	Profesor bloquearHabilitarProfesor(Profesor entity, boolean bloquear) throws BusinessException;
	
}

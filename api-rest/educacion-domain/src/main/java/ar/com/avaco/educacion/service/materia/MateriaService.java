package ar.com.avaco.educacion.service.materia;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Materia;

public interface MateriaService extends NJService<Long, Materia> {

	/**
	 * Obtiene las materias filtradas por nivel
	 * 
	 * @param idNivel id del nivel
	 * @return lista de materias
	 */
	List<Materia> listByNivel(Integer idNivel);
	
	/**
	 * Obtiene las materias filtradas por profesor
	 * 
	 * @param idProfesor id del profesor
	 * @return lista de materias
	 */
	List<Materia> listByProfesor(Long idProfesor);
	
	/**
	 * Crea una nueva materia
	 * 
	 * @param materia a grabar
	 * @return materia creada
	 * @throws BusinessException error de negocio
	 */
	Materia createMateria(Materia entity) throws BusinessException;

	/**
	 * Actualiza una materia
	 * 
	 * @param entity materia a grabar
	 * @return institucion actualizada
	 * @throws materia error de negocio
	 */
	Materia updateMateria(Materia entity) throws BusinessException;
	
}

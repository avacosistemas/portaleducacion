package ar.com.avaco.educacion.service.institucion;

import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Institucion;

public interface InstitucionService extends NJService<Long, Institucion> {

	/**
	 * Obtiene las instituciones donde se encuentra un alumno especifico
	 * 
	 * @param idAlumno id del alumno
	 * @return lista de instituciones
	 */
	List<Institucion> listByAlumno(Long idAlumno);
	
	/**
	 * Crea una nueva institucion
	 * 
	 * @param entity  institucion a grabar
	 * @return institucion creada
	 * @throws BusinessException error de negocio
	 */
	Institucion createInstitucion(Institucion entity) throws BusinessException;
	
	/**
	 * Actualiza una institucion
	 * 
	 * @param entity  institucion a grabar
	 * @return institucion actualizada
	 * @throws BusinessException error de negocio
	 */
	Institucion updateInstitucion(Institucion entity) throws BusinessException;
	
}

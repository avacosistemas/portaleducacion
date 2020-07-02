package ar.com.avaco.educacion.service.nivel;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Nivel;

public interface NivelService extends NJService<Integer, Nivel> {

	/**
	 * Crea un nuevo nivel
	 * 
	 * @param entity nivel a grabar
	 * @return nivel creado
	 * @throws BusinessException error de negocio
	 */
	Nivel createNivel(Nivel entity) throws BusinessException;
	
	/**
	 * Actualiza un nivel
	 * 
	 * @param entity nivel a grabar
	 * @return nivel actualizado
	 * @throws BusinessException error de negocio
	 */
	Nivel updateNivel(Nivel entity) throws BusinessException;
	
	
}

/**
 * 
 */
package ar.com.avaco.educacion.ws.service;

import ar.com.avaco.commons.exception.BusinessException;

public interface ParametrosPortalEPService {

	public Double getTasaPrestamoInversor() throws BusinessException;

	public Double getTasaPrestamoSolicitante() throws BusinessException;

	public Double getTasaFactoringInversor() throws BusinessException;

	public Double getTasaFactoringSolicitante() throws BusinessException;
	
}

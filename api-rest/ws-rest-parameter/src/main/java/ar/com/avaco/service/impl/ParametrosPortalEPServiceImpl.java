package ar.com.avaco.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.service.ParameterService;
import ar.com.avaco.service.ParametrosPortalEPService;

@Service("parametrosPortalEPService")
public class ParametrosPortalEPServiceImpl implements ParametrosPortalEPService {

	private ParameterService parameterService;
	
	@Override
	public Double getTasaPrestamoInversor() throws BusinessException {
		String findByKey = parameterService.findByKey("TASA_PRESTAMO_SOLICITANTE_SIMULADOR");
		return Double.parseDouble(findByKey);
	}

	@Override
	public Double getTasaPrestamoSolicitante() throws BusinessException {
		String findByKey = parameterService.findByKey("TASA_PRESTAMO_INVERSOR_SIMULADOR");
		return Double.parseDouble(findByKey);
	}
	
	@Resource(name = "parameterService")
	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

	@Override
	public Double getTasaFactoringInversor() throws BusinessException {
		String findByKey = parameterService.findByKey("TASA_FACTORING_INVERSOR_SIMULADOR");
		return Double.parseDouble(findByKey);
	}

	@Override
	public Double getTasaFactoringSolicitante() throws BusinessException {
		String findByKey = parameterService.findByKey("TASA_FACTORING_SOLICITANTE_SIMULADOR");
		return Double.parseDouble(findByKey);
	}

}

package ar.com.avaco.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ar.com.avaco.commons.domain.Parameter;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.commons.service.ParameterService;
import ar.com.avaco.service.ParameterEPService;
import ar.com.avaco.ws.dto.ParameterDTO;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("parameterEPService")
public class ParameterEPServiceImpl extends CRUDEPBaseService<Integer, ParameterDTO, Parameter, ParameterService>
		implements ParameterEPService {

	@Override
	protected Parameter convertToEntity(ParameterDTO dto) {
		Parameter p = new Parameter();
		p.setId(dto.getId());
		p.setKey(dto.getKey());
		p.setValue(dto.getValue());
		p.setDescription(dto.getDescription());
		return p;
	}

	@Override
	protected ParameterDTO convertToDto(Parameter entity) {
		ParameterDTO dto = new ParameterDTO();
		dto.setId(entity.getId());
		dto.setKey(entity.getKey());
		dto.setValue(entity.getValue());
		dto.setDescription(entity.getDescription());
		return dto;
	}
	
	@Override
	public ParameterDTO update(ParameterDTO dto) throws BusinessException {
		Parameter entity = convertToEntity(dto);
		Parameter original = service.get(entity.getId());
		validate(entity, original);
		entity = service.save(map(entity, original));
		return convertToDto(entity);
	}
	
	private Parameter map(Parameter entity, Parameter original) {
		original.setDescription(entity.getDescription());
		original.setValue(entity.getValue());
		return original;
	}

	private void validate(Parameter entity, Parameter original) throws ErrorValidationException {
		Map<String, String> errors =new HashMap<>();
		/*if(Validations.isParameterKeyValid(entity.getKey())) {
			errors.put("key", "El campo clave debe comenzar con letras y solo se permite letras, números, guion medio y bajo");
		} Consultar a Victor */
		if(original == null) {
			throw new UsernameNotFoundException("Usuario inexistente");
		}
		if(StringUtils.isEmpty(entity.getValue())) {
			errors.put("value", "El campo valor es requerido");
		}
		if(entity.getKey() != null && !original.getKey().equals(entity.getKey())) {
			errors.put("key", "El campo clave no es modificable");
		}
		if(!errors.isEmpty()) {
			throw new ErrorValidationException("Se encontraron los siguientes errores", errors);
		}
	}
	
	@Override
	@Resource(name = "parameterService")
	public void setService(ParameterService parameterService) {
		this.service = parameterService;
	}

}

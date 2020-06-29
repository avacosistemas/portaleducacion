package ar.com.avaco.educacion.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Nivel;
import ar.com.avaco.educacion.service.nivel.NivelService;
import ar.com.avaco.educacion.ws.dto.NivelDTO;
import ar.com.avaco.educacion.ws.service.NivelEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("nivelEPService")
public class NivelEPServiceImpl extends CRUDEPBaseService<Integer, NivelDTO, Nivel, NivelService> implements NivelEPService {

	
	@Override
	protected Nivel convertToEntity(NivelDTO dto) {
		Nivel nivel = new Nivel();
		nivel.setId(dto.getId());
		nivel.setDescripcion(dto.getDescripcion());
	
		return nivel;
	}

	@Override
	protected NivelDTO convertToDto(Nivel entity) {
		NivelDTO nivelDto = new NivelDTO();
		
		nivelDto.setId(entity.getId());
		nivelDto.setDescripcion(entity.getDescripcion());
		
		return nivelDto;
	}
	
	@Override
	public NivelDTO createNivel(NivelDTO nivelDto) throws BusinessException {
		Nivel nivel = nivelDto.toEntity();
		nivel = service.createNivel(nivel);
		return new NivelDTO(nivel);
	}

	@Override
	public NivelDTO updateNivel(Integer id, NivelDTO nivelDto) throws BusinessException {
		Nivel nivel = nivelDto.toEntity();
		nivel.setId(id);
		nivel = service.updateNivel(nivel);
		return new NivelDTO(nivel);
	}
	
	
	//Service
	@Override
	@Resource(name = "nivelService")
	protected void setService(NivelService nivelService) {
		this.service = nivelService;
	}


}

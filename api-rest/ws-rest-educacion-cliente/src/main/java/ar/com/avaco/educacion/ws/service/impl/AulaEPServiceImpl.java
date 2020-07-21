package ar.com.avaco.educacion.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.service.aula.AulaService;
import ar.com.avaco.educacion.ws.dto.AulaDTO;
import ar.com.avaco.educacion.ws.service.AulaEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("aulaEPService")
public class AulaEPServiceImpl extends CRUDEPBaseService<Long, AulaDTO, Aula, AulaService> implements AulaEPService {

	@Override
	protected AulaDTO convertToDto(Aula aula) {
		AulaDTO aulaDTO = new AulaDTO(aula);
		return aulaDTO;
	}

	@Override
	protected Aula convertToEntity(AulaDTO aulaDTO) {
		return aulaDTO.toEntity();
	}
	
	@Override
	@Resource(name = "aulaService")
	protected void setService(AulaService aulaService) {
		this.service = aulaService;
	}
	
	@Override
	public List<AulaDTO> listAulas() {		
		List<Aula> aulas=this.service.getAulas();
		List<AulaDTO> aulasDTOS=convertToDtos(aulas);
		return aulasDTOS;
	}

	@Override
	public AulaDTO getAula(Long id) {
		Aula aula= this.getService().getAula(id);
		AulaDTO aulaDTO = new AulaDTO(aula);
		return aulaDTO;
	}

	@Override
	public AulaDTO crearAula(AulaDTO aulaDTO) throws BusinessException {
		Aula aula= convertToEntity(aulaDTO);
		aula = service.crearAula(aula);
		return new AulaDTO(aula);
	}

	@Override
	public AulaDTO updateAula(Long id, AulaDTO aulaDTO) throws BusinessException {
		Aula aula= convertToEntity(aulaDTO);
		aula.setId(id);
		aula = service.updateAula(aula);
		return new AulaDTO(aula);
	}
	
}

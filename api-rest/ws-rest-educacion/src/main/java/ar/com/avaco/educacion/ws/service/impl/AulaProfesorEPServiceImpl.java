package ar.com.avaco.educacion.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.service.aula.AulaService;
import ar.com.avaco.educacion.ws.dto.AulaProfesorDTO;
import ar.com.avaco.educacion.ws.service.AulaProfesorEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("aulaProfesorEPService")
public class AulaProfesorEPServiceImpl extends CRUDEPBaseService<Long, AulaProfesorDTO, Aula, AulaService> implements AulaProfesorEPService {
	
	@Override
	public List<AulaProfesorDTO> listAulaProfesor(Long idAula) throws BusinessException {
		Aula aula = this.getService().getAula(idAula);
		
		List<AulaProfesorDTO> convertToDtos = this.convertToDtos(aula);
		return convertToDtos;
	}
	

	public List<AulaProfesorDTO> convertToDtos(Aula aula) {
		
		AulaProfesorDTO aulaProfesorDto;
		
		List<AulaProfesorDTO> dtos = new ArrayList<>();
		
		for (Profesor entity : aula.getProfesores()) {
		
			aulaProfesorDto = new AulaProfesorDTO();
			
			aulaProfesorDto.setIdAula(aula.getId());
			aulaProfesorDto.setIdProfesor(entity.getId());
			
			dtos.add(aulaProfesorDto);
		}
		return dtos;
	}
	
	@Override
	public AulaProfesorDTO addProfesor(AulaProfesorDTO aulaProfesorDTO) throws BusinessException {				
		Aula aula = service.addProfesorAula(aulaProfesorDTO.getIdAula(), aulaProfesorDTO.getIdProfesor());
		
		AulaProfesorDTO dto=new AulaProfesorDTO();
		dto.setIdAula(aula.getId());
		dto.setIdProfesor(aulaProfesorDTO.getIdProfesor());
		
		return aulaProfesorDTO;
	}
	
	@Override
	public void removeAulaProfesor(Long idAula, Long idProfesor) throws BusinessException {
		service.removeAulaProfesor(idAula, idProfesor);	
	}

	@Override
	protected Aula convertToEntity(AulaProfesorDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AulaProfesorDTO convertToDto(Aula entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	@Resource(name = "aulaService")
	protected void setService(AulaService aulaService) {
		this.service = aulaService;
	}
	
	
}

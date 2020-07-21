package ar.com.avaco.educacion.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.service.aula.AulaService;
import ar.com.avaco.educacion.ws.dto.AulaAlumnoDTO;
import ar.com.avaco.educacion.ws.service.AulaAlumnoEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("aulaAlumnoEPService")
public class AulaAlumnoEPServiceImpl extends CRUDEPBaseService<Long, AulaAlumnoDTO, Aula, AulaService> implements AulaAlumnoEPService {
	
	@Override
	public List<AulaAlumnoDTO> listAulaAlumno(Long idAula) throws BusinessException {
		Aula aula = this.getService().getAula(idAula);
		
		List<AulaAlumnoDTO> convertToDtos = this.convertToDtos(aula);
		return convertToDtos;
	}
	

	public List<AulaAlumnoDTO> convertToDtos(Aula aula) {
		
		AulaAlumnoDTO aulaAlumnoDto;
		
		List<AulaAlumnoDTO> dtos = new ArrayList<>();
		
		for (Alumno entity : aula.getAlumnos()) {
		
			aulaAlumnoDto = new AulaAlumnoDTO();
			
			aulaAlumnoDto.setIdAula(aula.getId());
			aulaAlumnoDto.setIdAlumno(entity.getId());
			
			dtos.add(aulaAlumnoDto);
		}
		return dtos;
	}
	
	@Override
	public AulaAlumnoDTO addAlumno(AulaAlumnoDTO aulaAlumnoDTO) throws BusinessException {				
		Aula aula = service.addAlumnoAula(aulaAlumnoDTO.getIdAula(), aulaAlumnoDTO.getIdAlumno());
		
		AulaAlumnoDTO dto=new AulaAlumnoDTO();
		dto.setIdAula(aula.getId());
		dto.setIdAlumno(aulaAlumnoDTO.getIdAlumno());
		
		return aulaAlumnoDTO;
	}
	
	@Override
	public void removeAulaAlumno(Long idAula, Long idAlumno) throws BusinessException {
		service.removeAulaAlumno(idAula, idAlumno);	
	}

	@Override
	protected Aula convertToEntity(AulaAlumnoDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AulaAlumnoDTO convertToDto(Aula entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	@Resource(name = "aulaService")
	protected void setService(AulaService aulaService) {
		this.service = aulaService;
	}
	
	
}

package ar.com.avaco.educacion.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.AulaAlumno;
import ar.com.avaco.educacion.service.aula.AulaAlumnoService;
import ar.com.avaco.educacion.ws.dto.AulaAlumnoDTO;
import ar.com.avaco.educacion.ws.service.AulaAlumnoEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("aulaAlumnoEPService")
public class AulaAlumnoEPServiceImpl extends CRUDEPBaseService<Long, AulaAlumnoDTO, AulaAlumno, AulaAlumnoService> implements AulaAlumnoEPService {
	
	@Override
	public List<AulaAlumnoDTO> listAulaAlumno(Long idAula) throws BusinessException {
		List<AulaAlumno> listByAula = new ArrayList<>();
		if (idAula != null) {
			listByAula = this.getService().listByAula(idAula);
		} else {
			listByAula = this.getService().list();
		}
		List<AulaAlumnoDTO> convertToDtos = this.convertToDtos(listByAula);
		return convertToDtos;
	}

	@Override
	public AulaAlumnoDTO save(AulaAlumnoDTO dto) throws BusinessException {
		AulaAlumno aulaAlumno = convertToEntity(dto);
		this.service.saveAlumno(aulaAlumno);
		return convertToDto(aulaAlumno);
	}
	
	@Override
	protected AulaAlumno convertToEntity(AulaAlumnoDTO dto) {
		AulaAlumno aa = new AulaAlumno();
		Alumno alumno = new Alumno();
		alumno.setId(Long.parseLong(dto.getIdAlumno()));
		aa.setAlumno(alumno );
		Aula aula = new Aula();
		aula.setId(Long.parseLong(dto.getIdAula()));
		aa.setAula(aula);
		return aa;
	}

	@Override
	protected AulaAlumnoDTO convertToDto(AulaAlumno entity) {
		AulaAlumnoDTO aulaAlumnoDto = new AulaAlumnoDTO();
		aulaAlumnoDto.setIdAula(entity.getAula().getId().toString());
		aulaAlumnoDto.setIdAlumno(entity.getId().toString());
		aulaAlumnoDto.setId(entity.getId());
		aulaAlumnoDto.setNombreAlumno(entity.getAlumno().getNombreApellido());
		aulaAlumnoDto.setComentario(entity.getComentario());
		aulaAlumnoDto.setCalificacion(entity.getCalificacion() != null &&  entity.getCalificacion() > 0D ? entity.getCalificacion() : null);
		return aulaAlumnoDto;
	}

	
	@Override
	@Resource(name = "aulaAlumnoService")
	protected void setService(AulaAlumnoService aulaAlumnoService) {
		this.service = aulaAlumnoService;
	}
	
	
}

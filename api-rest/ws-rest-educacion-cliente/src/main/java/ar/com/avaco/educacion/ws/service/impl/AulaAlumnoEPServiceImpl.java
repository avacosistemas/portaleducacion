package ar.com.avaco.educacion.ws.service.impl;

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
public class AulaAlumnoEPServiceImpl extends CRUDEPBaseService<Long, AulaAlumnoDTO, AulaAlumno, AulaAlumnoService>
		implements AulaAlumnoEPService {

	@Override
	public List<AulaAlumnoDTO> listAulaAlumno(Long idAula) throws BusinessException {
		List<AulaAlumno> alumnos = getService().listByAula(idAula);
		List<AulaAlumnoDTO> convertToDtos = this.convertToDtos(alumnos);
		return convertToDtos;
	}

	@Override
	protected AulaAlumno convertToEntity(AulaAlumnoDTO dto) {
		AulaAlumno aa = new AulaAlumno();
		Alumno alumno = new Alumno();
		alumno.setId(dto.getIdAlumno());
		aa.setAlumno(alumno);
		Aula aula = new Aula();
		aula.setId(dto.getIdAula());
		aa.setAula(aula);
		aa.setId(dto.getId());
		return aa;
	}

	@Override
	@Resource(name = "aulaAlumnoService")
	protected void setService(AulaAlumnoService aulaAlumnoService) {
		this.service = aulaAlumnoService;
	}

	@Override
	protected AulaAlumnoDTO convertToDto(AulaAlumno entity) {
		AulaAlumnoDTO aulaAlumnoDto = new AulaAlumnoDTO();
		aulaAlumnoDto.setIdAula(entity.getAula().getId());
		aulaAlumnoDto.setIdAlumno(entity.getId());
		aulaAlumnoDto.setCalificacion(entity.getCalificacion());
		aulaAlumnoDto.setComentario(entity.getComentario());
		return aulaAlumnoDto;
	}

}

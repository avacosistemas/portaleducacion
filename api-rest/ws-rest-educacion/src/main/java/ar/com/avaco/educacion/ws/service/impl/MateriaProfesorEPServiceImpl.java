package ar.com.avaco.educacion.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Nivel;
import ar.com.avaco.educacion.service.materia.MateriaService;
import ar.com.avaco.educacion.ws.dto.MateriaProfesorDTO;
import ar.com.avaco.educacion.ws.service.MateriaProfesorEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("materiaProfesorEPService")
public class MateriaProfesorEPServiceImpl extends CRUDEPBaseService<Long, MateriaProfesorDTO, Materia, MateriaService> implements MateriaProfesorEPService {

	@Override
	public List<MateriaProfesorDTO> listByProfesor(Long idProfesor) {
		List<Materia> materias = this.getService().listByProfesor(idProfesor);
		List<MateriaProfesorDTO> convertToDtos = convertToDtos(materias);
		materias = null;
		return convertToDtos;
	}
		
	
	@Override
	protected Materia convertToEntity(MateriaProfesorDTO dto) {
		Materia materia = new Materia();
		materia.setId(dto.getId());
		materia.setDescripcion(dto.getDescMateria());
		
		Nivel nivel = new Nivel();
		nivel.setId(dto.getIdNivel());
		nivel.setDescripcion(dto.getDescNivel());
	
		return materia;
	}

	@Override
	protected MateriaProfesorDTO convertToDto(Materia entity) {
		MateriaProfesorDTO materiaProfesorDto = new MateriaProfesorDTO();
		
		materiaProfesorDto.setId(entity.getId());
		materiaProfesorDto.setDescMateria(entity.getDescripcion());
		
		materiaProfesorDto.setIdNivel(entity.getNivel().getId());
		materiaProfesorDto.setDescNivel(entity.getNivel().getDescripcion());
		
		return materiaProfesorDto;
	}

	@Override
	public MateriaProfesorDTO createMateriaProfesor(Long idMateria, Long idProfesor) throws BusinessException {
		Materia materia = service.createMateriaProfesor(idMateria, idProfesor);
		return new MateriaProfesorDTO(materia);
	}

	//Service
	@Override
	@Resource(name = "materiaService")
	protected void setService(MateriaService materiaService) {
		this.service = materiaService;
	}

}

package ar.com.avaco.educacion.ws.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.service.materia.MateriaService;
import ar.com.avaco.educacion.ws.dto.MateriaDTO;
import ar.com.avaco.educacion.ws.service.MateriaEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("materiaEPService")
public class MateriaEPServiceImpl extends CRUDEPBaseService<Long, MateriaDTO, Materia, MateriaService> implements MateriaEPService {

	@Override
	protected Materia convertToEntity(MateriaDTO dto) {
		Materia materia = new Materia();
		materia.setId(dto.getId());
		materia.setDescripcion(dto.getDescripcion());
	
		return materia;
	}

	@Override
	protected MateriaDTO convertToDto(Materia entity) {
		MateriaDTO materiaDto = new MateriaDTO();
		
		materiaDto.setId(entity.getId());
		materiaDto.setDescripcion(entity.getDescripcion());
		
		return materiaDto;
	}
	
	@Override
	public MateriaDTO createMateria(MateriaDTO materiaDto) throws BusinessException {
		Materia materia = materiaDto.toEntity();
		materia = service.createMateria(materia);
		return new MateriaDTO(materia);
	}

	@Override
	public MateriaDTO updateMateria(Long id, MateriaDTO materiaDto) throws BusinessException {
		Materia materia = materiaDto.toEntity();
		materia.setId(id);
		materia = service.updateMateria(materia);
		return new MateriaDTO(materia);
	}
	
	
	//Service
	@Override
	@Resource(name = "materiaService")
	protected void setService(MateriaService materiaService) {
		this.service = materiaService;
	}


}

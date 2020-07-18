package ar.com.avaco.educacion.ws.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Nivel;
import ar.com.avaco.educacion.service.materia.MateriaService;
import ar.com.avaco.educacion.ws.dto.MateriaDTO;
import ar.com.avaco.educacion.ws.dto.MateriaGridDTO;
import ar.com.avaco.educacion.ws.service.MateriaEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("materiaEPService")
public class MateriaEPServiceImpl extends CRUDEPBaseService<Long, MateriaDTO, Materia, MateriaService> implements MateriaEPService {
	
	
	@Override
	public List<MateriaGridDTO> listGrid() {
		List<Materia> list = this.service.list();		
		return list.stream()
				.map(m -> new MateriaGridDTO(m))
				.collect(Collectors.toList());
	}

	@Override
	public List<MateriaDTO> listByNivel(Integer idNivel) {
		List<Materia> materias = this.getService().listByNivel(idNivel);
		List<MateriaDTO> convertToDtos = convertToDtos(materias);
		materias = null;
		return convertToDtos;
	}
	
	@Override
	public MateriaDTO createMateria(MateriaDTO materiaDto) throws BusinessException {
		Materia materia = new Materia();
		
		Nivel nivel = new Nivel();
		nivel.setId(materiaDto.getIdNivel());
		materia.setDescripcion(materiaDto.getDescripcion());
		materia.setNivel(nivel);
	
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
	
	@Override
	public MateriaDTO updateGridMateria(Long id, MateriaGridDTO materiaDto) throws BusinessException {
		Materia materia = materiaDto.toEntity();
		materia.setId(id);
		materia = service.updateMateria(materia);
		return new MateriaDTO(materia);
	}
	
	@Override
	protected Materia convertToEntity(MateriaDTO dto) {
		Materia materia = new Materia();
		materia.setId(dto.getId());
		materia.setDescripcion(dto.getDescripcion());
		
		Nivel nivel = new Nivel();
		nivel.setId(dto.getIdNivel());
		
		materia.setNivel(nivel);
	
		return materia;
	}

	@Override
	protected MateriaDTO convertToDto(Materia entity) {
		MateriaDTO materiaDto = new MateriaDTO();
		
		materiaDto.setId(entity.getId());
		materiaDto.setDescripcion(entity.getDescripcion());
		materiaDto.setIdNivel(entity.getNivel().getId());
		materiaDto.setDescripcionNivel(entity.getDescripcion() + " (Nivel: "+ entity.getNivel().getDescripcion() +")");
		
		
		return materiaDto;
	}
	
	//Service
	@Override
	@Resource(name = "materiaService")
	protected void setService(MateriaService materiaService) {
		this.service = materiaService;
	}

}

package ar.com.avaco.educacion.ws.service.impl;

import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.service.profesor.ProfesorService;
import ar.com.avaco.educacion.ws.dto.MateriaProfesorDTO;
import ar.com.avaco.educacion.ws.dto.MateriaProfesorFullDTO;
import ar.com.avaco.educacion.ws.service.MateriaProfesorEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("materiaProfesorEPService")
public class MateriaProfesorEPServiceImpl extends CRUDEPBaseService<Long, MateriaProfesorFullDTO, Profesor, ProfesorService> implements MateriaProfesorEPService {


	@Override
	public List<MateriaProfesorFullDTO> listMateriasProfesor(Long id) {
		Profesor profesor = this.getService().getMateriaProfesor(id);
	
		List<MateriaProfesorFullDTO> convertToDtos = this.convertToDtos(profesor);
		profesor = null;
		return convertToDtos;
	}
	

	@Override
	public MateriaProfesorDTO createMateriaProfesor(MateriaProfesorDTO materiaProfesor) throws BusinessException {
		
		Long idMateria = materiaProfesor.getIdMateria();
		Long idProfesor = materiaProfesor.getIdProfesor();
		
		Profesor profesor = service.createMateriaProfesor(idMateria, idProfesor);
		
		MateriaProfesorDTO dto = new MateriaProfesorDTO();
		dto.setIdMateria(idMateria);
		dto.setIdProfesor(profesor.getId());
		
		/*Optional<Materia> materiaOpt = profesor.getMaterias().stream().filter(mat -> mat.getId().equals(idMateria)).findFirst();
		
		if(materiaOpt.isPresent()) {
			
			Materia materia = materiaOpt.get();
			dto.setIdMateria(idMateria);
			dto.setDescMateria(materia.getDescripcion());
			
			dto.setIdNivel(materia.getNivel().getId());
			dto.setDescNivel(materia.getNivel().getDescripcion());

		}*/
		
		return dto;
	}
	
	@Override
	public void removeMateriaProfesor(Long idProfesor, Long idMateria) throws BusinessException {
		service.removeMateriaProfesor(idMateria, idProfesor);
	}
	
	public List<MateriaProfesorFullDTO> convertToDtos(Profesor profesor) {
		
		MateriaProfesorFullDTO materiaProfesorDto;
		List<MateriaProfesorFullDTO> dtos = new ArrayList<>();
		for (Materia entity : profesor.getMaterias()) {
		
			materiaProfesorDto = new MateriaProfesorFullDTO();
			
			materiaProfesorDto.setId(profesor.getId());
			
			materiaProfesorDto.setIdMateria(entity.getId());
			materiaProfesorDto.setDescMateria(entity.getDescripcion());
			
			materiaProfesorDto.setIdNivel(entity.getNivel().getId());
			materiaProfesorDto.setDescNivel(entity.getNivel().getDescripcion());
			
			
			dtos.add(materiaProfesorDto);
		}
		return dtos;
	}

	//Service
	@Override
	@Resource(name = "profesorService")
	protected void setService(ProfesorService profesorService) {
		this.service = profesorService;
	}

	@Override
	protected Profesor convertToEntity(MateriaProfesorFullDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MateriaProfesorFullDTO convertToDto(Profesor entity) {
		// TODO Auto-generated method stub
		return null;
	}




}

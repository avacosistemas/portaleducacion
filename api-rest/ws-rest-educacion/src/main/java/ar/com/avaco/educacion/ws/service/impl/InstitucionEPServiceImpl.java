package ar.com.avaco.educacion.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Institucion;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.service.institucion.InstitucionService;
import ar.com.avaco.educacion.ws.dto.InstitucionDTO;
import ar.com.avaco.educacion.ws.dto.MateriaDTO;
import ar.com.avaco.educacion.ws.service.InstitucionEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("institucionEPService")
public class InstitucionEPServiceImpl extends CRUDEPBaseService<Long, InstitucionDTO, Institucion, InstitucionService> implements InstitucionEPService {


	@Override
	public List<InstitucionDTO> listByAlumno(Long idAlumno) {
		List<Institucion> instituciones = this.getService().listByAlumno(idAlumno);
		List<InstitucionDTO> convertToDtos = convertToDtos(instituciones);
		instituciones = null;
		return convertToDtos;
	}
		
	@Override
	public InstitucionDTO createInstitucion(InstitucionDTO institucionDto) throws BusinessException {

		Institucion institucion = new Institucion();
		institucion.setNombre(institucionDto.getNombre());

		institucion = service.createInstitucion(institucion);
		return new InstitucionDTO(institucion);
		
	}

	@Override
	public InstitucionDTO updateInstitucion(Long id, InstitucionDTO institucionDto) throws BusinessException {
		Institucion institucion = institucionDto.toEntity();
		institucion.setId(id);
		institucion = service.updateInstitucion(institucion);
		return new InstitucionDTO(institucion);
	}
	
	
	//Service
	@Override
	@Resource(name = "institucionService")
	protected void setService(InstitucionService institucionService) {
		this.service = institucionService;
	}
	
	@Override
	protected Institucion convertToEntity(InstitucionDTO dto) {
		Institucion institucion = new Institucion();
		institucion.setId(dto.getId());
		institucion.setNombre(dto.getNombre());
	
		return institucion;
	}

	@Override
	protected InstitucionDTO convertToDto(Institucion entity) {
		InstitucionDTO institucionDto = new InstitucionDTO();
		
		institucionDto.setId(entity.getId());
		institucionDto.setNombre(entity.getNombre());
		
		return institucionDto;
	}
	
}

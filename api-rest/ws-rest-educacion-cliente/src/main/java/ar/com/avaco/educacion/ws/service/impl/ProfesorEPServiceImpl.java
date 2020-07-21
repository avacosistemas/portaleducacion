package ar.com.avaco.educacion.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Identificacion;
import ar.com.avaco.educacion.domain.entities.cliente.TipoIdentificacion;
import ar.com.avaco.educacion.service.profesor.ProfesorService;
import ar.com.avaco.educacion.ws.dto.ProfesorDTO;
import ar.com.avaco.educacion.ws.service.ProfesorEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("profesorEPService")
public class ProfesorEPServiceImpl extends CRUDEPBaseService<Long, ProfesorDTO, Profesor, ProfesorService> implements ProfesorEPService {


	@Override
	public ProfesorDTO getProfesor(Long id) {
		Profesor profesor = this.getService().getProfesor(id);
		ProfesorDTO profesorDTO = new ProfesorDTO(profesor);
		return profesorDTO;
	}
	
	@Override
	public List<ProfesorDTO> listProfesores() {
		List<Profesor> profesores = this.getService().listProfesores();
		List<ProfesorDTO> convertToDtos = convertToDtos(profesores);
		profesores = null;
		return convertToDtos;
	}
	
	@Override
	public ProfesorDTO updateProfesor(Long id, ProfesorDTO profesorDto) throws BusinessException {
		Profesor profesor = profesorDto.toEntity();
		profesor.setId(id);
		profesor = service.updateProfesor(profesor);
		return new ProfesorDTO(profesor);
	}
	
	@Override
	public ProfesorDTO createProfesor(ProfesorDTO profesorDTO) throws BusinessException {
		Profesor profesor = convertToEntity(profesorDTO);
		profesor = service.createProfesor(profesor);
		return new ProfesorDTO(profesor);
		
	}
	
	@Override
	public ProfesorDTO bloquearHabilitarProfesor(Long id, boolean bloquear) throws BusinessException {
		Profesor profesor = new Profesor();
		profesor.setId(id);
		profesor = service.bloquearHabilitarProfesor(profesor, bloquear);
		return new ProfesorDTO(profesor);
	}
	
	@Override
	protected Profesor convertToEntity(ProfesorDTO dto) {
		
		Profesor profesor = new Profesor();
		profesor.setId(dto.getId());
		profesor.setRazonSocialNombreApellido(dto.getNombreApellido());
	
		Identificacion id = new Identificacion();
		id.setTipo(TipoIdentificacion.valueOf(dto.getTipoIdentificacion()));
		id.setNumero(dto.getNumeroIdentificacion());
		id.setCliente(profesor);
		profesor.setIdentificacion(id);
		
		Contacto contacto = new Contacto();
		contacto.setTelefonoMovil(dto.getTelefonoMovil());
		contacto.setCliente(profesor);
		profesor.setContacto(contacto);
	
		profesor.setUsername(dto.getUsername());
		profesor.setEmail(dto.getEmail());
	
		return profesor;
	}

	@Override
	protected ProfesorDTO convertToDto(Profesor entity) {
		ProfesorDTO profesorDTO = new ProfesorDTO();
		profesorDTO.setId(entity.getId());
		profesorDTO.setNombreApellido(entity.getRazonSocialNombreApellido());
		profesorDTO.setTipoIdentificacion(entity.getIdentificacion().getTipo().name());
		profesorDTO.setNumeroIdentificacion(entity.getIdentificacion().getNumero());
		profesorDTO.setUsername(entity.getUsername());
		profesorDTO.setEmail(entity.getEmail());
		profesorDTO.setTelefonoMovil(entity.getContacto().getTelefonoMovil());
		return profesorDTO;
	}

	//Service
	@Override
	@Resource(name = "profesorService")
	protected void setService(ProfesorService profesorService) {
		this.service = profesorService;
	}

}

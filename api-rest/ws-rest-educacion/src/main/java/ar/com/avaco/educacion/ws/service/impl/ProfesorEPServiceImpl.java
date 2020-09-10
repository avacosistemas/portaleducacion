package ar.com.avaco.educacion.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		ProfesorDTO profesorDTO = convertToDto(profesor);
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
		Profesor profesor = this.service.get(profesorDto.getId());

		profesor.setId(profesorDto.getId());
		profesor.setNombre(profesorDto.getNombre());
		profesor.setApellido(profesorDto.getApellido());
		profesor.setUsername(profesorDto.getUsername());
		profesor.setEmail(profesorDto.getEmail());
		profesor.setDescripcion(profesorDto.getDescripcion());
		profesor.setTitulo(profesorDto.getTitulo());
		
		if (profesorDto.getFoto() != null) {
			profesor.setFoto(profesorDto.getFoto());
		}
		
		Identificacion identificacion = profesor.getIdentificacion();
		identificacion.setNumero(profesorDto.getNumeroIdentificacion());
		TipoIdentificacion ti = TipoIdentificacion.DNI;
		if (profesorDto.getTipoIdentificacion() != null) {
			ti = TipoIdentificacion.valueOf(profesorDto.getTipoIdentificacion());
		}
		identificacion.setTipo(ti);
		

		Contacto contacto = profesor.getContacto();
		contacto.setTelefonoMovil(profesorDto.getTelefonoMovil());
		contacto.setTelefonoFijo(profesorDto.getTelefonoFijo());

		if (StringUtils.isNotBlank(profesorDto.getValorHora())) {
			profesor.setValorHora(Double.parseDouble(profesorDto.getValorHora()));
		} else {
			profesor.setValorHora(null);
		}

		profesor = service.updateProfesor(profesor);

		return convertToDto(profesor);
	}
	
	@Override
	public ProfesorDTO updateFotoPerfil(Long id, MultipartFile file) throws BusinessException {
		Profesor profesor = service.uploadFotoPerfil(id, file);
		profesor = service.createProfesor(profesor);
	
		return convertToDto(profesor);
	}
	
	@Override
	public byte[] downloadFotoPerfil(Long id) throws BusinessException {
		return service.downloadFotoPerfil(id);
	}
	
	@Override
	public ProfesorDTO createProfesor(ProfesorDTO profesorDTO) throws BusinessException {
		Profesor profesor = convertToEntity(profesorDTO);
		profesor = service.createProfesor(profesor);
		return convertToDto(profesor);
		
	}
	
	@Override
	public ProfesorDTO bloquearHabilitarProfesor(Long id, boolean bloquear) throws BusinessException {
		Profesor profesor = new Profesor();
		profesor.setId(id);
		profesor = service.bloquearHabilitarProfesor(profesor, bloquear);
		return convertToDto(profesor);
	}
	
	@Override
	protected Profesor convertToEntity(ProfesorDTO dto) {
		
		Profesor profesor = new Profesor();
		profesor.setId(dto.getId());
		profesor.setNombre(dto.getNombre());
		profesor.setApellido(dto.getApellido());
	
		Identificacion id = new Identificacion();
		id.setTipo(TipoIdentificacion.valueOf(dto.getTipoIdentificacion()));
		id.setNumero(dto.getNumeroIdentificacion());
		id.setCliente(profesor);
		profesor.setIdentificacion(id);
		
		Contacto contacto = new Contacto();
		contacto.setTelefonoMovil(dto.getTelefonoMovil());
		contacto.setTelefonoFijo(dto.getTelefonoFijo());
		contacto.setCliente(profesor);
		profesor.setContacto(contacto);
	
		profesor.setUsername(dto.getUsername());
		profesor.setEmail(dto.getEmail());
	
		profesor.setDescripcion(dto.getDescripcion());
		
		profesor.setTitulo(dto.getTitulo());
		
		if (StringUtils.isNotBlank(dto.getValorHora())) {
			profesor.setValorHora(Double.parseDouble(dto.getValorHora()));
		} else {
			profesor.setValorHora(null);
		}
		
		return profesor;
	}

	@Override
	protected ProfesorDTO convertToDto(Profesor entity) {
		ProfesorDTO profesorDTO = new ProfesorDTO();
		profesorDTO.setId(entity.getId());
		profesorDTO.setNombre(entity.getNombre());
		profesorDTO.setApellido(entity.getApellido());
		profesorDTO.setTipoIdentificacion(entity.getIdentificacion().getTipo().name());
		profesorDTO.setNumeroIdentificacion(entity.getIdentificacion().getNumero());
		profesorDTO.setUsername(entity.getUsername());
		profesorDTO.setEmail(entity.getEmail());
		profesorDTO.setTelefonoMovil(entity.getContacto().getTelefonoMovil());
		profesorDTO.setTelefonoFijo(entity.getContacto().getTelefonoFijo());
		profesorDTO.setNombreApellido(entity.getNombre() + " " + entity.getApellido());
		profesorDTO.setDescripcion(entity.getDescripcion());
		profesorDTO.setTitulo(entity.getTitulo());
		profesorDTO.setBloqueado(entity.isBloqueado());
		profesorDTO.setHabilitado(!entity.isBloqueado());
		profesorDTO.setEstado(entity.isBloqueado() ? "Bloqueado" : "Habilitado");
		profesorDTO.setCalificacion(entity.getCalificacion());
		profesorDTO.setTieneFoto(entity.getFoto() != null);
		if (entity.getValorHora() != null && entity.getValorHora() > 0) {
			profesorDTO.setValorHora(entity.getValorHora().toString());
			profesorDTO.setValorHoraString("$ "  + entity.getValorHora().toString());
		}
		return profesorDTO;
	}

	//Service
	@Override
	@Resource(name = "profesorService")
	protected void setService(ProfesorService profesorService) {
		this.service = profesorService;
	}

}

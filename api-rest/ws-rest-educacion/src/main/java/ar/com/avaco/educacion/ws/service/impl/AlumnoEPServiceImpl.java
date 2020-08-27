package ar.com.avaco.educacion.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ar.com.avaco.educacion.domain.entities.Institucion;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Identificacion;
import ar.com.avaco.educacion.domain.entities.cliente.TipoIdentificacion;
import ar.com.avaco.educacion.service.alumno.AlumnoService;
import ar.com.avaco.educacion.ws.dto.AlumnoDTO;
import ar.com.avaco.educacion.ws.dto.RegistroAlumnoDTO;
import ar.com.avaco.educacion.ws.service.AlumnoEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("alumnoEPService")
public class AlumnoEPServiceImpl extends CRUDEPBaseService<Long, AlumnoDTO, Alumno, AlumnoService> implements AlumnoEPService {


	@Override
	public AlumnoDTO getAlumno(Long id) {
		Alumno alumno = this.getService().getAlumno(id);
		AlumnoDTO alumnoDTO = convertToDto(alumno);
		return alumnoDTO;
	}
	
	@Override
	public List<AlumnoDTO> listAlumnos() {
		List<Alumno> alumnos = this.getService().listAlumnos();
		List<AlumnoDTO> convertToDtos = convertToDtos(alumnos);
		alumnos = null;
		return convertToDtos;
	}

	@Override
	public List<AlumnoDTO> listAlumnosByInstitucion(Long idInstitucion) {
		List<Alumno> alumnos = this.getService().listAlumnosByInstitucion(idInstitucion);
		List<AlumnoDTO> convertToDtos = convertToDtos(alumnos);
		alumnos = null;
		return convertToDtos;
	}
	
	@Override
	public AlumnoDTO updateAlumno(Long id, AlumnoDTO alumnoDto) throws BusinessException {
		Alumno alumno = this.service.getAlumno(id);
		
		alumno.setNombre(alumnoDto.getNombre());
		alumno.setApellido(alumnoDto.getApellido());
		Identificacion identificacion = alumno.getIdentificacion();
		if (alumnoDto.getTipoIdentificacion() != null) {
			identificacion.setTipo(TipoIdentificacion.valueOf(alumnoDto.getTipoIdentificacion().toUpperCase()));
		} else {
			identificacion.setTipo(TipoIdentificacion.DNI);
		}
		identificacion.setNumero(alumnoDto.getNumeroIdentificacion());
		
		Contacto contacto = alumno.getContacto();
		contacto.setTelefonoMovil(alumnoDto.getTelefonoMovil());
		alumno.setEmail(alumnoDto.getEmail());

		if (alumnoDto.getIdInstitucion() != null && alumnoDto.getIdInstitucion() > 0) {
			Institucion institucion = new Institucion();
			institucion.setId(alumnoDto.getIdInstitucion());
			alumno.setInstitucion(institucion);
		}
		alumno = service.updateAlumno(alumno);
		return convertToDto(alumno);
	}
	
	@Override
	public AlumnoDTO createAlumno(AlumnoDTO alumnoDto) throws BusinessException {
		Alumno alumno = convertToEntity(alumnoDto);
		alumno = service.createAlumno(alumno);
		return convertToDto(alumno);
		
	}	
	
	
	@Override
	public AlumnoDTO registrarAlumno(RegistroAlumnoDTO alumnoDto) throws BusinessException {
		validarPassword(alumnoDto);
		Alumno alumno =  convertToEntity(alumnoDto);
		alumno.setPassword(alumnoDto.getPassword());
		alumno = service.registrarAlumno(alumno);
		return convertToDto(alumno);
	}

	/**
	 * Valida los password antes de enviar al service
	 * 
	 * @param alumnoDto
	 * @throws BusinessException
	 */
	private void validarPassword(RegistroAlumnoDTO alumnoDto) throws BusinessException {
		if (alumnoDto.getPassword()==null)
			throw new BusinessException("Debe Ingresar una contraseña");
		if (alumnoDto.getSecondPassword()==null)
			throw new BusinessException("Debe Repetir la contraseña");
		if (!alumnoDto.getPassword().equals(alumnoDto.getSecondPassword()))
			throw new BusinessException("No coindicen las contraseñas");
	}

	@Override
	public AlumnoDTO bloquearHabilitarAlumno(Long id, boolean bloquear) throws BusinessException {
		Alumno alumno = new Alumno();
		alumno.setId(id);
		alumno = service.bloquearHabilitarAlumno(alumno, bloquear);
		return convertToDto(alumno);
	}

	@Override
	protected Alumno convertToEntity(AlumnoDTO dto) {
		
		Alumno alumno = new Alumno();
		alumno.setId(dto.getId());
		alumno.setNombre(dto.getNombre());
		alumno.setApellido(dto.getApellido());
		Identificacion id = new Identificacion();
		if (dto.getTipoIdentificacion() != null) {
			id.setTipo(TipoIdentificacion.valueOf(dto.getTipoIdentificacion().toUpperCase()));
		} else {
			id.setTipo(TipoIdentificacion.DNI);
		}
		id.setNumero(dto.getNumeroIdentificacion());
		id.setCliente(alumno);
		alumno.setIdentificacion(id);
		
		Contacto contacto = new Contacto();
		contacto.setTelefonoMovil(dto.getTelefonoMovil());
		contacto.setCliente(alumno);
		alumno.setIdentificacion(id);
		alumno.setContacto(contacto);
		alumno.setUsername(dto.getUsername());
		alumno.setEmail(dto.getEmail());

		if (dto.getIdInstitucion() != null && dto.getIdInstitucion() > 0) {
			Institucion institucion = new Institucion();
			institucion.setId(dto.getIdInstitucion());
			alumno.setInstitucion(institucion);
		}


		return alumno;
	}

	@Override
	protected AlumnoDTO convertToDto(Alumno entity) {
		AlumnoDTO alumnoDTO = new AlumnoDTO();
		alumnoDTO.setId(entity.getId());
		alumnoDTO.setNombre(entity.getNombre());
		alumnoDTO.setApellido(entity.getApellido());
		alumnoDTO.setTipoIdentificacion(entity.getIdentificacion().getTipo().name());
		alumnoDTO.setNumeroIdentificacion(entity.getIdentificacion().getNumero());
		alumnoDTO.setUsername(entity.getUsername());
		alumnoDTO.setEmail(entity.getEmail());
		alumnoDTO.setTelefonoMovil(entity.getContacto().getTelefonoMovil());
		alumnoDTO.setNombreApellido(entity.getNombreApellido());
		if (entity.getInstitucion() != null) {
			alumnoDTO.setIdInstitucion(entity.getInstitucion().getId());
			alumnoDTO.setNombreInstitucion(entity.getInstitucion().getNombre());
		}
		alumnoDTO.setBloqueado(entity.isBloqueado());
		alumnoDTO.setHabilitado(!entity.isBloqueado());
		alumnoDTO.setEstado(entity.isBloqueado() ? "Bloqueado" : "Habilitado");
		return alumnoDTO;
	}


	//Service
	@Override
	@Resource(name = "alumnoService")
	protected void setService(AlumnoService alumnoService) {
		this.service = alumnoService;
	}


}

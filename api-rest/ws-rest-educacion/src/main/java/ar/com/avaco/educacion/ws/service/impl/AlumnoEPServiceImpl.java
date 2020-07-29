package ar.com.avaco.educacion.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ar.com.avaco.educacion.domain.entities.Institucion;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Identificacion;
import ar.com.avaco.educacion.domain.entities.cliente.TipoIdentificacion;
import ar.com.avaco.educacion.service.alumno.AlumnoService;
import ar.com.avaco.educacion.ws.dto.AlumnoDTO;
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
	public AlumnoDTO updateAlumno(Long id, AlumnoDTO alumnoDto) throws BusinessException {
		Alumno alumno = convertToEntity(alumnoDto);
		alumno.setId(id);
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
		id.setTipo(TipoIdentificacion.valueOf(dto.getTipoIdentificacion()));
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
		return alumnoDTO;
	}


	//Service
	@Override
	@Resource(name = "alumnoService")
	protected void setService(AlumnoService alumnoService) {
		this.service = alumnoService;
	}


}

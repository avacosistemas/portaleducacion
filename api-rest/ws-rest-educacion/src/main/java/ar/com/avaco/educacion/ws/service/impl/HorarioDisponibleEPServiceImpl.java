package ar.com.avaco.educacion.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.educacion.domain.entities.Dia;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.service.disponibilidad.HorarioDisponibleService;
import ar.com.avaco.educacion.ws.dto.HorarioDisponibleDTO;
import ar.com.avaco.educacion.ws.service.HorarioDisponibleEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("horarioDisponibleEPService")
public class HorarioDisponibleEPServiceImpl extends CRUDEPBaseService<Long, HorarioDisponibleDTO, HorarioDisponible, HorarioDisponibleService> implements HorarioDisponibleEPService {


	@Override
	public List<HorarioDisponibleDTO> listHorariosDispProfesor(Long idProfesor) {
		List<HorarioDisponible> horarios = this.getService().listHorariosDispProfesor(idProfesor);
		List<HorarioDisponibleDTO> convertToDtos = convertToDtos(horarios);
		horarios = null;
		return convertToDtos;
	}

	@Override
	protected HorarioDisponible convertToEntity(HorarioDisponibleDTO dto) {
				
		HorarioDisponible disponibilidad = new HorarioDisponible();
		disponibilidad.setId(dto.getId());
		disponibilidad.setDia(Dia.valueOf(dto.getDia()));
		disponibilidad.setHora(dto.getHora());
		
		Profesor profesor = new Profesor();
		profesor.setId(dto.getIdProfesor());
		disponibilidad.setProfesor(profesor);
	
		return disponibilidad;
	}

	@Override
	protected HorarioDisponibleDTO convertToDto(HorarioDisponible entity) {
		
		HorarioDisponibleDTO disponibilidadDTO = new HorarioDisponibleDTO();
		disponibilidadDTO.setId(entity.getId());
		//disponibilidadDTO.setIdProfesor(entity.getProfesor().getId());
		disponibilidadDTO.setDia(entity.getDia().name());
		disponibilidadDTO.setHora(entity.getHora());
		
		return disponibilidadDTO;
	}


	//Service
	@Override
	@Resource(name = "horarioDisponibleService")
	protected void setService(HorarioDisponibleService horarioDisponibleService) {
		this.service = horarioDisponibleService;
	}



}

package ar.com.avaco.educacion.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Dia;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.service.disponibilidad.HorarioDisponibleService;
import ar.com.avaco.educacion.ws.dto.HorarioDisponibleDTO;
import ar.com.avaco.educacion.ws.dto.HorarioDisponibleFullDTO;
import ar.com.avaco.educacion.ws.service.HorarioDisponibleEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("horarioDisponibleEPService")
public class HorarioDisponibleEPServiceImpl extends CRUDEPBaseService<Long, HorarioDisponibleDTO, HorarioDisponible, HorarioDisponibleService> implements HorarioDisponibleEPService {


	@Override
	public List<HorarioDisponibleDTO> listHorariosDispProfesor(Long idProfesor) {
		List<HorarioDisponible> horarios = this.getService().listHorariosDispProfesor(idProfesor);
		List<HorarioDisponibleDTO> convertToDtos = this.convertToDtos(horarios);
		horarios = null;
		return convertToDtos;
	}

	@Override
	public List<HorarioDisponibleDTO> addHorarioDisponible(HorarioDisponibleFullDTO horariosDispDto) throws BusinessException {
		
		List<HorarioDisponible> horariosToAdd = new ArrayList<>();
		HorarioDisponible horarioDisp;
	
		for(int i=horariosDispDto.getHoraDesde(); i <= horariosDispDto.getHoraHasta(); i++) {
	
			if(horariosDispDto.isLunes()) {
				horarioDisp = new HorarioDisponible();
			
				horarioDisp.setDia(Dia.LUNES);
				horarioDisp.setHora(String.valueOf(i));
				horariosToAdd.add(horarioDisp);
			}
			
			if(horariosDispDto.isMartes()) {
				horarioDisp = new HorarioDisponible();
				horarioDisp.setDia(Dia.MARTES);
				horarioDisp.setHora(String.valueOf(i));
				horariosToAdd.add(horarioDisp);
		
			}
			
			if(horariosDispDto.isMiercoles()) {
				horarioDisp = new HorarioDisponible();
			
				horarioDisp.setDia(Dia.MIERCOLES);
				horarioDisp.setHora(String.valueOf(i));
				horariosToAdd.add(horarioDisp);
		
			}
			
			if(horariosDispDto.isJueves()) {
				horarioDisp = new HorarioDisponible();
			
				horarioDisp.setDia(Dia.JUEVES);
				horarioDisp.setHora(String.valueOf(i));
				horariosToAdd.add(horarioDisp);
		
			}
			
			if(horariosDispDto.isViernes()) {
				horarioDisp = new HorarioDisponible();
		
				horarioDisp.setDia(Dia.VIERNES);
				horarioDisp.setHora(String.valueOf(i));
				horariosToAdd.add(horarioDisp);
		
			}
			
			if(horariosDispDto.isSabado()) {
				horarioDisp = new HorarioDisponible();
		
				horarioDisp.setDia(Dia.SABADO);
				horarioDisp.setHora(String.valueOf(i));
				horariosToAdd.add(horarioDisp);
		
			}
			
			if(horariosDispDto.isDomingo()) {
				horarioDisp = new HorarioDisponible();
			
				horarioDisp.setDia(Dia.DOMINGO);
				horarioDisp.setHora(String.valueOf(i));
				horariosToAdd.add(horarioDisp);
		
			}
			
		}
		
		List<HorarioDisponible> horarios = this.getService().addHorariosDisponibles(horariosToAdd, horariosDispDto.getId());
		
		List<HorarioDisponibleDTO> convertToDtos = this.convertToDtos(horarios, horariosDispDto.getId());
		horarios = null;
		return convertToDtos;
	}
	
	public List<HorarioDisponibleDTO> convertToDtos(List<HorarioDisponible> horarios, Long idProfesor) {
		List<HorarioDisponibleDTO> dtos = new ArrayList<>();
		
		HorarioDisponibleDTO disponibilidadDTO;
		for (HorarioDisponible entity : horarios) {
			disponibilidadDTO = convertToDto(entity);
			disponibilidadDTO.setIdProfesor(idProfesor);
			dtos.add(disponibilidadDTO);
		}
		return dtos;
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

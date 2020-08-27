package ar.com.avaco.educacion.ws.service.impl;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;

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
	
		if (horariosDispDto.getHoraHasta() == null) {
			horariosDispDto.setHoraHasta(horariosDispDto.getHoraDesde() + 1);
		}
		
		for(int i=horariosDispDto.getHoraDesde(); i <= horariosDispDto.getHoraHasta() - 1; i++) {
	
			if(horariosDispDto.isLunes()) {
				horarioDisp = new HorarioDisponible();
			
				horarioDisp.setDia(DayOfWeek.MONDAY);
				horarioDisp.setHora(i);
				horariosToAdd.add(horarioDisp);
			}
			
			if(horariosDispDto.isMartes()) {
				horarioDisp = new HorarioDisponible();
				horarioDisp.setDia(DayOfWeek.TUESDAY);
				horarioDisp.setHora(i);
				horariosToAdd.add(horarioDisp);
		
			}
			
			if(horariosDispDto.isMiercoles()) {
				horarioDisp = new HorarioDisponible();
			
				horarioDisp.setDia(DayOfWeek.WEDNESDAY);
				horarioDisp.setHora(i);
				horariosToAdd.add(horarioDisp);
		
			}
			
			if(horariosDispDto.isJueves()) {
				horarioDisp = new HorarioDisponible();
			
				horarioDisp.setDia(DayOfWeek.THURSDAY);
				horarioDisp.setHora(i);
				horariosToAdd.add(horarioDisp);
		
			}
			
			if(horariosDispDto.isViernes()) {
				horarioDisp = new HorarioDisponible();
		
				horarioDisp.setDia(DayOfWeek.FRIDAY);
				horarioDisp.setHora(i);
				horariosToAdd.add(horarioDisp);
		
			}
			
			if(horariosDispDto.isSabado()) {
				horarioDisp = new HorarioDisponible();
		
				horarioDisp.setDia(DayOfWeek.SATURDAY);
				horarioDisp.setHora(i);
				horariosToAdd.add(horarioDisp);
		
			}
			
			if(horariosDispDto.isDomingo()) {
				horarioDisp = new HorarioDisponible();
			
				horarioDisp.setDia(DayOfWeek.SUNDAY);
				horarioDisp.setHora(i);
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
			dtos.add(disponibilidadDTO);
		}
		return dtos;
	}

	@Override
	protected HorarioDisponible convertToEntity(HorarioDisponibleDTO dto) {
				
		HorarioDisponible disponibilidad = new HorarioDisponible();
		disponibilidad.setId(dto.getId());
		disponibilidad.setDia(DayOfWeek.of(dto.getNumeroDia()));

		disponibilidad.setHora(dto.getHora());
	
		return disponibilidad;
	}

	@Override
	protected HorarioDisponibleDTO convertToDto(HorarioDisponible entity) {
		
		HorarioDisponibleDTO disponibilidadDTO = new HorarioDisponibleDTO();
		disponibilidadDTO.setId(entity.getId());
		disponibilidadDTO.setNumeroDia(entity.getDia().getValue());
		disponibilidadDTO.setDia(entity.getDia().getDisplayName(TextStyle.FULL, new Locale("es","ES")).toUpperCase());
		Integer laHora = entity.getHora();
		Integer laHoraMasUno = laHora + 1 == 24 ? 0 : laHora + 1; 
		disponibilidadDTO.setHora(laHora);
		disponibilidadDTO.setRangoHora(laHora.toString() + ":00 a " + (laHoraMasUno).toString() + ":00 Hs.");
		return disponibilidadDTO;
	}


	//Service
	@Override
	@Resource(name = "horarioDisponibleService")
	protected void setService(HorarioDisponibleService horarioDisponibleService) {
		this.service = horarioDisponibleService;
	}



}

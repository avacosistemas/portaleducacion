package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Dia;
import ar.com.avaco.educacion.domain.entities.HorarioDisponible;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class HorarioDisponibleDTO extends DTOEntity<Long> {
	
	private Long id;
	private String dia;
	private String hora;

	public HorarioDisponibleDTO() {
	}

	public HorarioDisponibleDTO(HorarioDisponible disponibilidad) {
		this.setDTO(disponibilidad);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public HorarioDisponible toEntity() {

		HorarioDisponible disponibilidad = new HorarioDisponible();
		disponibilidad.setId(this.getId());
		disponibilidad.setDia(Dia.valueOf(this.getDia()));
		disponibilidad.setHora(this.getHora());

		return disponibilidad;
	}

	public void setDTO(HorarioDisponible disponibilidad) {
		this.setId(disponibilidad.getId());
	
		this.setDia(disponibilidad.getDia().name());
		this.setHora(disponibilidad.getHora());
	}

}

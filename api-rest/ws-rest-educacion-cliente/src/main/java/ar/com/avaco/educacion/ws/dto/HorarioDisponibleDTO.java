package ar.com.avaco.educacion.ws.dto;

import java.time.DayOfWeek;

import ar.com.avaco.educacion.domain.entities.HorarioDisponible;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class HorarioDisponibleDTO extends DTOEntity<Long> {

	private Long id;
	private Integer dia;
	private Integer hora;

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

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public HorarioDisponible toEntity() {

		HorarioDisponible disponibilidad = new HorarioDisponible();
		disponibilidad.setId(this.getId());
		disponibilidad.setDia(DayOfWeek.of(this.getDia()));
		disponibilidad.setHora(this.getHora());

		return disponibilidad;
	}

	public void setDTO(HorarioDisponible disponibilidad) {
		this.setId(disponibilidad.getId());

		this.setDia(disponibilidad.getDia().getValue());
		this.setHora(disponibilidad.getHora());
	}

}

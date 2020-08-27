package ar.com.avaco.educacion.ws.dto;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import ar.com.avaco.educacion.domain.entities.HorarioDisponible;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class HorarioDisponibleDTO extends DTOEntity<Long> {

	private Long id;
	private Integer numeroDia;
	private String dia;
	private Integer hora;
	private String rangoHora;

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

	public Integer getNumeroDia() {
		return numeroDia;
	}

	public void setNumeroDia(Integer numeroDia) {
		this.numeroDia = numeroDia;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
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
		disponibilidad.setDia(DayOfWeek.of(this.getNumeroDia()));
		disponibilidad.setHora(this.getHora());

		return disponibilidad;
	}

	public void setDTO(HorarioDisponible disponibilidad) {
		this.setId(disponibilidad.getId());
		this.setNumeroDia(disponibilidad.getDia().getValue());
		this.setDia(disponibilidad.getDia().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase());
		Integer laHora = disponibilidad.getHora();
		Integer laHoraMasUno = laHora + 1 == 24 ? 0 : laHora + 1; 
		this.setHora(laHora);
		this.setRangoHora(laHora.toString() + ":00 a " + (laHoraMasUno).toString() + ":00 Hs.");
	}

	public String getRangoHora() {
		return rangoHora;
	}

	public void setRangoHora(String rangoHora) {
		this.rangoHora = rangoHora;
	}

}

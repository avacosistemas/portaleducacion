package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.AulaEventos;
import ar.com.avaco.utils.DateUtils;

public class EventoBBBDTO {

	private String fecha;

	private String usuario;

	private String tipoUsuario;

	private String idAula;

	private String tipoEvento;

	public EventoBBBDTO() {
		// TODO Auto-generated constructor stub
	}

	public EventoBBBDTO(AulaEventos ae) {
		this.idAula = ae.getAula().getId().toString();
		this.fecha = DateUtils.toString(ae.getFecha(), "dd/MM/yyyy - HH:mm:ss");
		this.tipoEvento = ae.getTipoEvento().name();
		this.tipoUsuario = ae.getTipoUsuario().name();
		this.usuario = ae.getUsuario().getNombreApellido();
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getIdAula() {
		return idAula;
	}

	public void setIdAula(String idAula) {
		this.idAula = idAula;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

}

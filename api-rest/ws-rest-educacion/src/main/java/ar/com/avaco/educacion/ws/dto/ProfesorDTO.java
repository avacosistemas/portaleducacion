package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Identificacion;
import ar.com.avaco.educacion.domain.entities.cliente.TipoIdentificacion;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ProfesorDTO extends DTOEntity<Long> {
	
	private Long id;
	private String nombreApellido;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String username;
	private String email;
	private String telefonoMovil;

	public ProfesorDTO() {
	}

	public ProfesorDTO(Profesor profesor) {
		this.setDTO(profesor);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public Profesor toEntity() {

		Profesor profesor = new Profesor();
		profesor.setId(this.getId());
		profesor.setRazonSocialNombreApellido(this.getNombreApellido());
		profesor.setUsername(this.getUsername());
		profesor.setEmail(this.getEmail());
		
		Identificacion id = new Identificacion();
		id.setNumero(this.getNumeroIdentificacion());
		id.setTipo(TipoIdentificacion.valueOf(this.getTipoIdentificacion()));
		id.setCliente(profesor);
		profesor.setIdentificacion(id);
		
		Contacto contacto = new Contacto();
		contacto.setTelefonoMovil(this.getTelefonoMovil());
		contacto.setCliente(profesor);
		profesor.setIdentificacion(id);
	
		return profesor;
	}

	public void setDTO(Profesor profesor) {
	
		this.setId(profesor.getId());
		this.setNombreApellido(profesor.getRazonSocialNombreApellido());
		this.setTipoIdentificacion(profesor.getIdentificacion().getTipo().name());
		this.setNumeroIdentificacion(profesor.getIdentificacion().getNumero());
		this.setUsername(profesor.getUsername());
		this.setEmail(profesor.getEmail());
		this.setTelefonoMovil(profesor.getContacto().getTelefonoMovil());

	}

}

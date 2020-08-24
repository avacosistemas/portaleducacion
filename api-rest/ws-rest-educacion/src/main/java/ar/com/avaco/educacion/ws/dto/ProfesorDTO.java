package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Identificacion;
import ar.com.avaco.educacion.domain.entities.cliente.TipoIdentificacion;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ProfesorDTO extends DTOEntity<Long> {

	private Long id;
	private String nombre;
	private String apellido;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String username;
	private String email;
	private String telefonoMovil;

	private byte[] foto;

	private String nombreApellido;

	private String descripcion;

	public ProfesorDTO() {
	}

	public ProfesorDTO(Profesor profesor) {
		this.setDTO(profesor);
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Profesor toEntity() {

		Profesor profesor = new Profesor();
		profesor.setId(this.getId());
		profesor.setNombre(this.getNombre());
		profesor.setApellido(this.getApellido());
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
		profesor.setContacto(contacto);

		profesor.setDescripcion(this.getDescripcion());

		return profesor;
	}

	public void setDTO(Profesor profesor) {
		this.setId(profesor.getId());
		this.setNombre(profesor.getNombre());
		this.setApellido(profesor.getApellido());
		this.setFoto(profesor.getFoto());
		this.setTipoIdentificacion(profesor.getIdentificacion().getTipo().name());
		this.setNumeroIdentificacion(profesor.getIdentificacion().getNumero());
		this.setUsername(profesor.getUsername());
		this.setEmail(profesor.getEmail());
		this.setTelefonoMovil(profesor.getContacto().getTelefonoMovil());
		this.setNombreApellido(profesor.getNombre() + " " + profesor.getApellido());
		this.setDescripcion(profesor.getDescripcion());
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	
	
}

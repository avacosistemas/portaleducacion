package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Identificacion;
import ar.com.avaco.educacion.domain.entities.cliente.TipoIdentificacion;

public class ProfesorDTO extends ClienteDTO {

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

}

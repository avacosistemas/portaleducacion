package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Identificacion;
import ar.com.avaco.educacion.domain.entities.cliente.TipoIdentificacion;

public class AlumnoDTO extends ClienteDTO {

	public AlumnoDTO() {}

	public AlumnoDTO(Alumno alumno) {
		this.setDTO(alumno);
	}
	
	public Alumno toEntity() {

		Alumno alumno = new Alumno();
		alumno.setId(this.getId());
		alumno.setRazonSocialNombreApellido(this.getNombreApellido());
		alumno.setUsername(this.getUsername());
		alumno.setEmail(this.getEmail());
		
		Identificacion id = new Identificacion();
		id.setNumero(this.getNumeroIdentificacion());
		id.setTipo(TipoIdentificacion.valueOf(this.getTipoIdentificacion()));
		id.setCliente(alumno);
		alumno.setIdentificacion(id);
		
		Contacto contacto = new Contacto();
		contacto.setTelefonoMovil(this.getTelefonoMovil());
		contacto.setCliente(alumno);
		alumno.setContacto(contacto);
	
		return alumno;
	}

	public void setDTO(Alumno alumno) {
	
		this.setId(alumno.getId());
		this.setNombreApellido(alumno.getRazonSocialNombreApellido());
		this.setTipoIdentificacion(alumno.getIdentificacion().getTipo().name());
		this.setNumeroIdentificacion(alumno.getIdentificacion().getNumero());
		this.setUsername(alumno.getUsername());
		this.setEmail(alumno.getEmail());
		this.setTelefonoMovil(alumno.getContacto().getTelefonoMovil());

	}

}

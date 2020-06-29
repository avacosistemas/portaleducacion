package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Provincia;

public class ContactoDTO {

	private String domicilio;

	private String codigoPostal;

	private String barrio;

	private String localidad;

	/**
	 * Valores BUENOS_AIRES, CABA, CATAMARCA, CHACO, CHUBUT, CÓRDOBA, CORRIENTES,
	 * ENTRE_RIOS, FORMOSA, JUJUY, LA_PAMPA, LA_RIOJA, MENDOZA, MISIONES, NEUQUEN,
	 * RÍO_NEGRO, SALTA, SAN_JUAN, SANTA_CRUZ, SANTA_FE, SANTIAGO_DEL_ESTERO,
	 * TIERRA_DEL_FUEGO, TUCUMAN
	 */
	private String provincia;

	private String telefonoFijo;

	private String telefonoMovil;

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	
	public Contacto toEntity() {
		Contacto contacto = new Contacto();
		contacto.setBarrio(this.getBarrio());
		contacto.setCodigoPostal(this.getCodigoPostal());
		contacto.setDomicilio(this.getDomicilio());
		contacto.setLocalidad(this.getLocalidad());
		contacto.setProvincia(Provincia.valueOf(this.getProvincia()));
		contacto.setTelefonoFijo(this.getTelefonoFijo());
		contacto.setTelefonoMovil(this.getTelefonoMovil());
		return contacto;
	}

}

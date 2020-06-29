package ar.com.avaco.educacion.domain.entities.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "CLI_CONTACTO")
public class Contacto extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = -3260414467249766837L;

	/**
	 * Id de Identificacion igual al Id de Cliente. Al ser una relacion 1 a 1 es el
	 * mismo Id.
	 */
	@Id
	@GeneratedValue(generator = "foreigngencontacto")
	@GenericGenerator(strategy = "foreign", name = "foreigngencontacto", parameters = @Parameter(name = "property", value = "cliente"))
	@Column(name = "ID_CONTACTO")
	private Long id;

	/**
	 * El cliente al cual pertenece el contacto.
	 */
	@OneToOne(mappedBy = "contacto", optional = false)
	@PrimaryKeyJoinColumn
	private Cliente cliente;

	/**
	 * Nombre de la persona responsable con quien contactarse en caso de ser empresa.
	 */
	@Column(name = "NOMBRE_CONTACTO")
	private String nombreContacto;
	
	
	/**
	 * El domicilio completo.
	 */
	@Column(name = "DOMICILIO", nullable = false)
	private String domicilio;

	/**
	 * El codigo postal.
	 */
	@Column(name = "CODIGO_POSTAL", nullable = false)
	private String codigoPostal;

	/**
	 * El barrio.
	 */
	@Column(name = "BARRIO", nullable = false)
	private String barrio;

	/**
	 * Localidad.
	 */
	@Column(name = "LOCALIDAD", nullable = false)
	private String localidad;

	/**
	 * Provincia.
	 */
	@Enumerated
	@Column(name = "PROVINCIA", nullable = false)
	private Provincia provincia;

	/**
	 * Telefono fijo.
	 */
	@Column(name = "TEL_FIJO", nullable = false)
	private String telefonoFijo;

	/**
	 * Telefono celular.
	 */
	@Column(name = "TEL_CELULAR", nullable = false)
	private String telefonoMovil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
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

}

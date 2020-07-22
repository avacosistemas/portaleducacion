package ar.com.avaco.educacion.domain.entities.cliente;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import ar.com.avaco.arc.sec.domain.UserDetailsExtended;

/**
 * Un cliente del portal que realiza inversiones o toma prestamos.
 * 
 * @author beto
 *
 */
@Entity
@Table(name = "CLI_CLIENTE")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "CLIENTE_SEQ", sequenceName = "CLIENTE_SEQ", allocationSize = 1)
public class Cliente extends ar.com.avaco.arc.core.domain.Entity<Long> implements UserDetailsExtended {

	private static final long serialVersionUID = -5137938199082149370L;

	/**
	 * Cantidad maxima que el usuario puede intentar ingresar al sistema con
	 * contraseñas incorrectas.
	 */
	private static final int CANTIDAD_MAXIMA_INTENTOS_FALLIDOS = 10;

	/**
	 * Cantidad maxima de dias de uso del password.
	 */
	private static final int CANTIDAD_MAXIMA_DIAS_PASSWORD = 999999;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_SEQ")
	@Column(name = "ID_CLIENTE", unique = true, nullable = false)
	private Long id;

	/**
	 * El username para identificarse.
	 */
	@Column(name = "USERNAME", unique = true, updatable = false, nullable = false)
	private String username;

	/**
	 * La contraseña de ingreso .
	 */
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	/**
	 * Email unico por cliente.
	 */
	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	/**
	 * La identificación del cliente con tipo de documento y numero.
	 */
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Identificacion.class)
	@PrimaryKeyJoinColumn
	private Identificacion identificacion;

	/**
	 * La razon social de la empresa o el nombre y apellido del cliente.
	 * 
	 * @Column(name = "RS_NA", nullable = false) private String
	 *              razonSocialNombreApellido;
	 */

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "APELLIDO", nullable = false)
	private String apellido;

	/**
	 * La fecha de nacimiento del cliente o inicio de actividades si es empresa.
	 * 
	 * @Column(name = "FN_IA") private Date fechaNacimientoInicioActividades;
	 */

	@Column(name = "FECHA_NAC")
	private Date fechaNacimiento;

	/**
	 * Determina si es masculino, femenino o empresa.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "GENERO")
	private Genero genero;

	/**
	 * Nacionalidad.
	 */
	@Column(name = "NACIONALIDAD")
	private String nacionalidad;

	/**
	 * Contacto con el cliente.
	 */
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Contacto contacto;

	/**
	 * Determina si el usuario se encuentra bloqueado para ingresar al sistema.
	 */
	@Column(name = "BLOQUEADO")
	private boolean bloqueado;

	/**
	 * Determina si el usuario luego de loguearse debe cambiar su contraseña.
	 */
	@Column(name = "REQUIERE_CAMBIO_PASSWORD")
	private boolean requiereCambioPassword;

	/**
	 * Cantidad de intentos fallidos en que el usuario intento loguearse con una
	 * contraseña incorrecta.
	 */
	@Column(name = "INTENTOS_FALLIDOS_LOGIN")
	private Integer intentosFallidosLogin;

	/**
	 * Fecha de alta de nuevo password
	 */
	@Column(name = "FECHA_ALTA_PASSWORD")
	private Date fechaAltaPassword;

	/**
	 * Fecha en la que se registró el cliente.
	 */
	@Column(name = "FECHA_REGISTRO", nullable = false, updatable = false)
	private Date fechaRegistro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ACCESOS")
	private AccesosCliente accesos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Identificacion getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Identificacion identificacion) {
		this.identificacion = identificacion;
	}

	/*
	 * public String getRazonSocialNombreApellido() { return
	 * razonSocialNombreApellido; }
	 * 
	 * public void setRazonSocialNombreApellido(String razonSocialNombreApellido) {
	 * this.razonSocialNombreApellido = razonSocialNombreApellido; }
	 */

	/*
	 * public Date getFechaNacimientoInicioActividades() { return
	 * fechaNacimientoInicioActividades; }
	 * 
	 * public void setFechaNacimientoInicioActividades(Date
	 * fechaNacimientoInicioActividades) { this.fechaNacimientoInicioActividades =
	 * fechaNacimientoInicioActividades; }
	 */

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (PermisoCliente permiso : accesos.getPermisos()) {
			authorities.add(permiso);
		}
		return authorities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.intentosFallidosLogin < CANTIDAD_MAXIMA_INTENTOS_FALLIDOS;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !requiereCambioPassword && !passwordExpirado();
	}

	private boolean passwordExpirado() {
		boolean result = false;
		if (fechaAltaPassword != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaAltaPassword);
			calendar.add(Calendar.DAY_OF_YEAR, CANTIDAD_MAXIMA_DIAS_PASSWORD);
			result = Calendar.getInstance().getTime().after(calendar.getTime());
		}
		return result;
	}

	@Override
	public boolean isEnabled() {
		return !this.bloqueado;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public boolean isRequiereCambioPassword() {
		return requiereCambioPassword;
	}

	public void setRequiereCambioPassword(boolean requiereCambioPassword) {
		this.requiereCambioPassword = requiereCambioPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIntentosFallidosLogin() {
		return intentosFallidosLogin;
	}

	public void setIntentosFallidosLogin(Integer intentosFallidosLogin) {
		this.intentosFallidosLogin = intentosFallidosLogin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaAltaPassword() {
		return fechaAltaPassword;
	}

	public void setFechaAltaPassword(Date fechaAltaPassword) {
		this.fechaAltaPassword = fechaAltaPassword;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public AccesosCliente getAccesos() {
		return accesos;
	}

	public void setAccesos(AccesosCliente accesos) {
		this.accesos = accesos;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getIdString() {
		return "CLI" + StringUtils.leftPad(id.toString(), 10, "0");
	}

	public String getNombreApellido() {
		return this.getNombre() + " " + this.getApellido();
	}

}

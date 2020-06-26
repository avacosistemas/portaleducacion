package ar.com.avaco.educacion.ws.educacion.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ar.com.avaco.educacion.domain.cliente.Cliente;
import ar.com.avaco.educacion.domain.cliente.Contacto;
import ar.com.avaco.educacion.domain.cliente.Genero;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ClienteCompletoDTO extends DTOEntity<Long> {

	private Long id;

	private String username;

	private String email;

	private String tipoPerfil;

	private String tipoIdentificacion;

	private String numeroIdentificacion;

	private String razonSocial;

	private String fechaNacimiento;

	private String inicioActividades;

	private String genero;

	private String nacionalidad;

	private String estadoCivil;

	private Long hijosACargo = 0L;

	private Long mayoresACargo = 0L;

	private boolean politicamenteExpuesto = false;

	private String cargoPolitico;

	private ContactoDTO contactoDTO;

	private boolean bloqueado;

	private boolean requiereCambioPassword;

	private Integer intentosFallidosLogin;

	private String fechaAltaPassword;

	private String fechaRegistro;

	public ClienteCompletoDTO() {
	}

	public ClienteCompletoDTO(Cliente cliente) {
		this.setDTO(cliente);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTipoPerfil() {
		return tipoPerfil;
	}

	public void setTipoPerfil(String tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
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

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getInicioActividades() {
		return inicioActividades;
	}

	public void setInicioActividades(String inicioActividades) {
		this.inicioActividades = inicioActividades;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Long getHijosACargo() {
		return hijosACargo;
	}

	public void setHijosACargo(Long hijosACargo) {
		this.hijosACargo = hijosACargo;
	}

	public Long getMayoresACargo() {
		return mayoresACargo;
	}

	public void setMayoresACargo(Long mayoresACargo) {
		this.mayoresACargo = mayoresACargo;
	}

	public boolean isPoliticamenteExpuesto() {
		return politicamenteExpuesto;
	}

	public void setPoliticamenteExpuesto(boolean politicamenteExpuesto) {
		this.politicamenteExpuesto = politicamenteExpuesto;
	}

	public String getCargoPolitico() {
		return cargoPolitico;
	}

	public void setCargoPolitico(String cargoPolitico) {
		this.cargoPolitico = cargoPolitico;
	}

	public ContactoDTO getContactoDTO() {
		return contactoDTO;
	}

	public void setContactoDTO(ContactoDTO contactoDTO) {
		this.contactoDTO = contactoDTO;
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

	public Integer getIntentosFallidosLogin() {
		return intentosFallidosLogin;
	}

	public void setIntentosFallidosLogin(Integer intentosFallidosLogin) {
		this.intentosFallidosLogin = intentosFallidosLogin;
	}

	public String getFechaAltaPassword() {
		return fechaAltaPassword;
	}

	public void setFechaAltaPassword(String fechaAltaPassword) {
		this.fechaAltaPassword = fechaAltaPassword;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Cliente toEntity() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Cliente cliente = new Cliente();
		cliente.setEmail(this.getEmail());
		cliente.setGenero(Genero.valueOf(this.genero));
		try {
			cliente.setFechaNacimientoInicioActividades(df.parse(this.getFechaNacimiento()));
		} catch (Exception e1) {
			// No hacer nada. Dejar null.
		}
		try {
			cliente.setFechaNacimientoInicioActividades(df.parse(this.getInicioActividades()));
		} catch (Exception e1) {
			// No hacer nada. Dejar null.
		}
		cliente.setId(this.getId());
		cliente.setNacionalidad(this.getNacionalidad());
		cliente.setRazonSocialNombreApellido(this.getRazonSocial());

		Contacto contacto = this.contactoDTO.toEntity();
		contacto.setCliente(cliente);
		cliente.setContacto(contacto);

		df = null;

		return cliente;

	}

	public void setDTO(Cliente cliente) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.setBloqueado(cliente.isBloqueado());
		this.setEmail(cliente.getEmail());
		this.setFechaAltaPassword(
				cliente.getFechaAltaPassword() != null ? df.format(cliente.getFechaAltaPassword()) : null);
		this.setFechaNacimiento(df.format(cliente.getFechaNacimientoInicioActividades()));
		this.setInicioActividades(df.format(cliente.getFechaNacimientoInicioActividades()));
		this.setFechaRegistro(df.format(cliente.getFechaRegistro()));
		this.setGenero(cliente.getGenero().name());
		this.setId(cliente.getId());
		this.setIntentosFallidosLogin(cliente.getIntentosFallidosLogin());
		this.setNacionalidad(cliente.getNacionalidad());
		this.setNumeroIdentificacion(cliente.getIdentificacion().getNumero());
		this.setRazonSocial(cliente.getRazonSocialNombreApellido());
		this.setRequiereCambioPassword(cliente.isRequiereCambioPassword());
		this.setTipoIdentificacion(cliente.getIdentificacion().getTipo().name());
		this.setUsername(cliente.getUsername());

		ContactoDTO contactoDTO = new ContactoDTO();
		contactoDTO.setBarrio(cliente.getContacto().getBarrio());
		contactoDTO.setCodigoPostal(cliente.getContacto().getCodigoPostal());
		contactoDTO.setDomicilio(cliente.getContacto().getDomicilio());
		contactoDTO.setLocalidad(cliente.getContacto().getLocalidad());
		contactoDTO.setProvincia(cliente.getContacto().getProvincia().name());
		contactoDTO.setTelefonoFijo(cliente.getContacto().getTelefonoFijo());
		contactoDTO.setTelefonoMovil(cliente.getContacto().getTelefonoMovil());
		this.setContactoDTO(contactoDTO);

		df = null;

	}

}

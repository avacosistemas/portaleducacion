package ar.com.avaco.educacion.service.cliente;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.arc.sec.exception.NuclearJSecurityException;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.educacion.domain.entities.cliente.Cliente;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;
import ar.com.avaco.educacion.domain.entities.cliente.Genero;
import ar.com.avaco.educacion.domain.entities.cliente.Identificacion;
import ar.com.avaco.educacion.domain.entities.cliente.TipoIdentificacion;
import ar.com.avaco.educacion.repository.cliente.ClienteRepository;
import ar.com.avaco.educacion.service.notificacion.NotificacionService;
import ar.com.avaco.validation.Validations;

@Transactional
@Service("clienteService")
public class ClienteServiceImpl extends NJBaseService<Long, Cliente, ClienteRepository>
		implements ClienteService, UserDetailsService {

	private Integer INICIO_REINTENTOS_LOGIN = 0;

	/**
	 * The Password Encoder
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	private NotificacionService notificacionService;

	@Override
	public Cliente registrarClienteEmpresa(Cliente cliente) throws ErrorValidationException, BusinessException {
		validarClienteNoVacio(cliente);
		validarAltaCliente(cliente);
		validarContacto(cliente.getContacto());
		cliente = registrarCliente(cliente);
		return cliente;
	}

	@Override
	public Cliente registrarClientePersona(Cliente cliente) throws ErrorValidationException, BusinessException {
		validarClienteNoVacio(cliente);
		validarAltaCliente(cliente);
		validarContacto(cliente.getContacto());
		cliente = registrarCliente(cliente);
		return cliente;
	}

	/**
	 * Valida que la composición del cliente este completa, que no sea null y que la
	 * cuenta bancaria, el contacto y el ingreso tampoco sea null.
	 * 
	 * @param cliente
	 * @throws BusinessException
	 */
	private void validarClienteNoVacio(Cliente cliente) throws BusinessException {
		if (cliente == null) {
			throw new BusinessException("Cliente vacío.");
		} else if (cliente.getContacto() == null) {
			throw new BusinessException("Contacto vacío.");
		}
	}

	/**
	 * Ultimos detalles del registro del cliente. Lo pone como desbloqueado. Setea
	 * la cantidad de inicios de reintento de login en cero. Setea que debe
	 * cambiarse el password. Genera un password aleatorio y notifica al cliente la
	 * bienvenida por mail.
	 * 
	 * @param cliente
	 * @return
	 */
	private Cliente registrarCliente(Cliente cliente) {
		// Por default el cliente se da de desbloqueado.
		cliente.setBloqueado(false);

		// La cantidad de intentos de login es cero.
		cliente.setIntentosFallidosLogin(INICIO_REINTENTOS_LOGIN);

		// Por mas que luego se reenviara el password, se requerira cambio de password.
		cliente.setRequiereCambioPassword(true);

		String tmppass = generarPasswordAleatorio();
		cliente.setPassword(passwordEncoder.encode(tmppass));

		cliente.setFechaRegistro(Calendar.getInstance().getTime());

		cliente = getRepository().save(cliente);

		notificarPasswordANuevoCliente(cliente, tmppass);

		//cuentaService.crearCuenta(cliente);

		return cliente;
	}

	@Override
	public Cliente save(Cliente cliente) {
		return null;
	}

	@Override
	public Cliente update(Cliente cliente) {
		return null;
	}

	@Override
	public void updatePassword(Cliente cliente, String password, String newPassword) {
		if (!passwordEncoder.matches(password, cliente.getPassword())) {
			throw new NuclearJSecurityException("Contraseña actual inválida.");
		} else if (password.equals(newPassword)) {
			throw new NuclearJSecurityException("La nueva contraseña es igual a la actual.");
		}
		cliente.setPassword(passwordEncoder.encode(newPassword));
		cliente.setFechaAltaPassword(Calendar.getInstance().getTime());
		cliente.setRequiereCambioPassword(Boolean.FALSE);
		getRepository().saveAndFlush(cliente);
	}

	public void resetPassword(Long idCliente) {
		Cliente cliente = repository.findOne(idCliente);
		// La cantidad de intentos de login es cero.
		cliente.setIntentosFallidosLogin(INICIO_REINTENTOS_LOGIN);
		// Se requerira cambio de password.
		cliente.setRequiereCambioPassword(true);
		// Genero una password temporal
		String tmppass = generarPasswordAleatorio();
		cliente.setPassword(passwordEncoder.encode(tmppass));
		getRepository().saveAndFlush(cliente);
		notificacionService.notificarResetoPassword(cliente, tmppass);
	}

	protected void notificarResetoPassword(Cliente cliente, String tmpass) {
		notificacionService.notificarResetoPassword(cliente, tmpass);
	}

	private void notificarPasswordANuevoCliente(Cliente cliente, String tmpass) {
		notificacionService.notificarRegistroClienteNuevoPassword(cliente, tmpass);
	}

	/**
	 * Valida que el username, email y numero de identificacion no se encuentre
	 * registrado.
	 * 
	 * @param cliente el cliente a validar.
	 */
	public void validarAltaCliente(Cliente cliente) throws ErrorValidationException, BusinessException {

		if (cliente.getGenero() == null) {
			throw new BusinessException("No se puede determinar si el cliente es una empresa o una persona");
		}

		Map<String, String> errores = new HashMap<String, String>();

		boolean esEmpresa = cliente.getGenero().equals(Genero.EMPRESA);

		if (!esEmpresa && !Validations.isMayorEdad(cliente.getFechaNacimientoInicioActividades())) {
			errores.put("fechaNacimiento", "Debe ser mayor de 18 años para registrarse.");
		}

		// Validacion username
		if (StringUtils.isBlank(cliente.getUsername())) {
			errores.put("username", "El campo username es requerido.");
		} else if (cliente.getUsername().length() < 5) {
			errores.put("username", "El campo username debe tener al menos 5 caracteres.");
		} else if (!Validations.isUsernameValido(cliente.getUsername())) {
			errores.put("username",
					"El campo username debe empezar con una letra y tener al menos 5 caracteres alfanuméricos sin espacios.");
		} else if (getRepository().findByUsernameEqualsIgnoreCase(cliente.getUsername()) != null) {
			errores.put("username", "El username no esta disponible. Intente otro diferente.");
		}

		// Validacion nacionalidad
		if (!esEmpresa && !Validations.isNacionalidadValido(cliente.getNacionalidad())) {
			errores.put("nacionalidad", "El campo Nacionalidad solo permite letras y espacios");
		}

		// Validacion email
		if (!EmailValidator.getInstance().isValid(cliente.getEmail())) {
			errores.put("email", "El campo Email no tiene un formato válido.");
		} else if (getRepository().findByEmailEqualsIgnoreCase(cliente.getEmail()) != null) {
			errores.put("email", "El Email ingresado ya se encuentra registrado.");
		}

		// Validacion identificacion

		String numeroIdentificacion = cliente.getIdentificacion().getNumero();
		TipoIdentificacion tipoIdentificacion = cliente.getIdentificacion().getTipo();

		switch (tipoIdentificacion) {
		case DNI:
			if (esEmpresa) {
				throw new BusinessException("Tipo de identificación DNI no válido para Empresa");
			}

			if (StringUtils.isBlank(numeroIdentificacion)) {
				errores.put("numeroIdentificacion", "El campo DNI es requerido.");
			} else if (!Validations.isDNIValido(numeroIdentificacion)) {
				errores.put("numeroIdentificacion", "El campo DNI Debe contener 7 u 8 caracteres numéricos");
			}
			break;
		case CUIT:
			if (StringUtils.isBlank(numeroIdentificacion)) {
				errores.put("numeroIdentificacion", "El campo CUIT es requerido.");
			} else if (numeroIdentificacion.length() != 11) {
				errores.put("numeroIdentificacion", "El campo CUIT Debe tener exactamente 11 dígitos.");
			} else if (!Validations.isCuitValido(numeroIdentificacion)) {
				errores.put("numeroIdentificacion", "El campo CUIT debe comenzar con 20, 23, 24, 27, 30, 33 o 34.");
			} else if (!Validations.isDigitoVerificadorCuitCuilValido(numeroIdentificacion)) {
				errores.put("numeroIdentificacion", "El campo CUIT tiene el dígito verificador incorrecto.");
			}
			break;
		case CUIL:
			if (esEmpresa) {
				throw new BusinessException("Tipo de identificación CUIL no válido para Empresa");
			} else if (StringUtils.isBlank(numeroIdentificacion)) {
				errores.put("numeroIdentificacion", "El campo CUIL es requerido.");
			} else if (numeroIdentificacion.length() != 11) {
				errores.put("numeroIdentificacion", "El campo CUIL debe tener exactamente 11 dígitos.");
			} else if (!Validations.isCuilValido(numeroIdentificacion)) {
				errores.put("numeroIdentificacion", "El campo CUIL debe comenzar con 20, 23, 24 o 27.");
			} else if (!Validations.isDigitoVerificadorCuitCuilValido(numeroIdentificacion)) {
				errores.put("numeroIdentificacion", "El campo CUIL tiene el dígito verificador incorrecto.");
			}

			break;
		default:
			break;
		}

		Cliente porNumero = getRepository().findByIdentificacionNumeroLikeIgnoreCase(numeroIdentificacion);
		if (porNumero != null) {
			Identificacion identificacionPorNumeroEncontrado = porNumero.getIdentificacion();
			if (identificacionPorNumeroEncontrado.getTipo().equals(tipoIdentificacion)
					|| identificacionPorNumeroEncontrado.getNumero().substring(2, 9).equals(numeroIdentificacion)) {
				errores.put("numeroIdentificacion", tipoIdentificacion + " ya se encuentra registrado");
			}
		}

		if (!esEmpresa && StringUtils.isBlank(cliente.getNacionalidad())) {
			errores.put("nacionalidad", "El campo Nacionalidad es requerido.");
		}

		if (StringUtils.isBlank(cliente.getRazonSocialNombreApellido())) {
			if (esEmpresa) {
				errores.put("razonSocial", "El campo Razón Social es requerido.");
			} else {
				errores.put("nombreApellido", "El campo Nombre y Apellido es requerido.");
			}
		}

		if (!esEmpresa) {

			if (cliente.getFechaNacimientoInicioActividades() == null) {
				errores.put("fechaNacimiento", "El campo fecha de nacimiento es requerida.");
			}

		}

		if (!errores.isEmpty()) {
			throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}

	}

	@Override
	public void validarContacto(Contacto contacto) throws ErrorValidationException {

		Map<String, String> errores = new HashMap<String, String>();

		if (StringUtils.isBlank(contacto.getBarrio())) {
			errores.put("barrio", "El campo Barrio/Partido es requerido.");
		} else if (!Validations.isBarrioValido(contacto.getBarrio())) {
			errores.put("barrio", "El campo Barrio/Partido debe ser alfanumérico entre 2 y 80 caracteres.");
		}

		if (StringUtils.isBlank(contacto.getCodigoPostal())) {
			errores.put("codigoPostal", "Debe ingresar un Código Postal.");
		} else if (!Validations.isCodigoPostalValido(contacto.getCodigoPostal())) {
			errores.put("barrio",
					"El campo Código Postal debe ser de 4 dígitos (1234) o 1 letra, 4 dígitos y 3 letras al final (A1324CDE).");
		}

		if (StringUtils.isBlank(contacto.getDomicilio())) {
			errores.put("domicilio", "El campo Domicilio es requerido.");
		} else if (!Validations.isDomicilioValido(contacto.getDomicilio())) {
			errores.put("domicilio", "El campo Domicilio solo se permite letras, números, guion medio y punto.");
		}

		if (StringUtils.isBlank(contacto.getLocalidad())) {
			errores.put("localidad", "El campo Localidad/Partido es requerido.");
		} else if (!Validations.isLocalidadValido(contacto.getLocalidad())) {
			errores.put("localidad",
					"El campo Localidad/Partido solo se permite letras, números, guion medio y punto.");
		}

		if (StringUtils.isBlank(contacto.getTelefonoMovil()) && StringUtils.isBlank(contacto.getTelefonoFijo())) {
			errores.put("telefonoFijo", "Debe ingresar al menos un teléfono fijo o celular");
			errores.put("telefonoMovil", "Debe ingresar al menos un teléfono fijo o celular");
		} else {
			if (!StringUtils.isBlank(contacto.getTelefonoFijo())
					&& !Validations.isTelefonoValido(contacto.getTelefonoFijo())) {
				errores.put("telefonoFijo",
						"El campo Telefono Fijo debe contener exactamente 10 dígitos sin incluir el 0 de código de area. (Ej: 1148001234)");
			}
			if (StringUtils.isBlank(contacto.getTelefonoMovil())
					&& !Validations.isCelularValido(contacto.getTelefonoMovil())) {
				errores.put("telefonoMovil",
						"El campo Telefono movil debe contener exactamente 12 dígitos inclyendo el 15 despues del código de area sin 0. (Ej: 111560001234)");
			}
		}

		if (contacto.getProvincia() == null) {
			errores.put("provincia", "El campo Provincia es requerido.");
		}

		if (!errores.isEmpty()) {
			throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}

	}

	private String generarPasswordAleatorio() {
		String generateKey = KeyGenerators.string().generateKey();
		return generateKey;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getRepository().findByUsernameEquals(username);
	}

	@Override
	public Cliente getClientePorUsername(String username) {
		return getRepository().findByUsernameEquals(username);
	}

	@Override
	public Cliente getClientePorMail(String username) {
		return getRepository().findByEmailEqualsIgnoreCase(username);
	}

	@Override
	public Cliente getClienteCompleto(Long id) {
		return getRepository().getClienteCompleto(id);
	}

	@Override
	public Cliente updateClienteCompleto(Cliente entity) throws BusinessException {

		validarClienteNoVacio(entity);
		validarActualizacionDatosPersonalesCliente(entity);
		validarContacto(entity.getContacto());

		Cliente cliente = this.get(entity.getId());
		cliente.setFechaNacimientoInicioActividades(entity.getFechaNacimientoInicioActividades());
		cliente.setId(entity.getId());
		cliente.setNacionalidad(entity.getNacionalidad());

		cliente.getContacto().setBarrio(entity.getContacto().getBarrio());
		cliente.getContacto().setCodigoPostal(entity.getContacto().getCodigoPostal());
		cliente.getContacto().setDomicilio(entity.getContacto().getDomicilio());
		cliente.getContacto().setLocalidad(entity.getContacto().getLocalidad());
		cliente.getContacto().setProvincia(entity.getContacto().getProvincia());
		cliente.getContacto().setTelefonoFijo(entity.getContacto().getTelefonoFijo());
		cliente.getContacto().setTelefonoMovil(entity.getContacto().getTelefonoMovil());

		cliente = this.getRepository().save(cliente);

		return cliente;
	}

	@Override
	public List<Cliente> listClientesListado() {
		return getRepository().listClientesListado();
	}

	/**
	 * Validaciones para actualizar datos de un cliente.
	 * 
	 * @param cliente
	 * @throws ErrorValidationException
	 * @throws BusinessException
	 */
	public void validarActualizacionDatosPersonalesCliente(Cliente cliente)
			throws ErrorValidationException, BusinessException {

		Map<String, String> errores = new HashMap<String, String>();

		boolean esEmpresa = cliente.getGenero().equals(Genero.EMPRESA);

		if (!esEmpresa && StringUtils.isBlank(cliente.getNacionalidad())) {
			errores.put("nacionalidad", "Debe ingresar la nacionalidad");
		}

		if (StringUtils.isBlank(cliente.getRazonSocialNombreApellido())) {
			if (esEmpresa) {
				errores.put("razonSocial", "Debe ingresar la razón social de la empresa.");
			} else {
				errores.put("nombreApellido", "Debe ingresar su nombre y apellido.");
			}
		}

		if (!esEmpresa) {

			if (cliente.getFechaNacimientoInicioActividades() == null) {
				errores.put("fechaNacimiento", "Debe ingresar su fecha de Nacimiento.");
			}

		}

		if (!errores.isEmpty()) {
			throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}

	}


	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Resource(name = "clienteRepository")
	void setClienteRepository(ClienteRepository clienteRepository) {
		this.repository = clienteRepository;
	}

	@Resource(name = "notificacionService")
	public void setNotificacionService(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}

}

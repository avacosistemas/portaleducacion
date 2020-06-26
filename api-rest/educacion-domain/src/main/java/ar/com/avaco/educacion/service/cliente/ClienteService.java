package ar.com.avaco.educacion.service.cliente;

import java.util.List
;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.educacion.domain.cliente.Cliente;
import ar.com.avaco.educacion.domain.cliente.Contacto;

public interface ClienteService extends NJService<Long, Cliente> {

	Cliente getClientePorUsername(String username);

	Cliente registrarClientePersona(Cliente cliente) throws ErrorValidationException, BusinessException;

	Cliente registrarClienteEmpresa(Cliente cliente) throws ErrorValidationException, BusinessException;

	void validarAltaCliente(Cliente cliente) throws ErrorValidationException, BusinessException;

	void validarContacto(Contacto contacto) throws ErrorValidationException;

	Cliente getClienteCompleto(Long id);

	List<Cliente> listClientesListado();

	Cliente updateClienteCompleto(Cliente entity) throws BusinessException;

	void validarActualizacionDatosPersonalesCliente(Cliente cliente) throws ErrorValidationException, BusinessException;

	void resetPassword(Long id);
	
	void updatePassword(Cliente user, String password,String newPassword);

	Cliente getClientePorMail(String username);

}

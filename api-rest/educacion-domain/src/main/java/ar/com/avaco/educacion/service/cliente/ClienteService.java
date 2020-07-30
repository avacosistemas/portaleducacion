package ar.com.avaco.educacion.service.cliente;

import java.util.List
;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.educacion.domain.entities.cliente.Cliente;
import ar.com.avaco.educacion.domain.entities.cliente.Contacto;

public interface ClienteService extends NJService<Long, Cliente> {

	Cliente getClientePorUsername(String username);

	Cliente registrarClientePersona(Cliente cliente) throws ErrorValidationException, BusinessException;

	void validaContactoCliente(Contacto contacto) throws ErrorValidationException;

	Cliente getClienteCompleto(Long id);

	List<Cliente> listClientesListado();

	Cliente validaUpdateProfesorAlumno(Cliente cliente) throws BusinessException;

	void resetPassword(Long id);
	
	void updatePassword(Cliente user, String password,String newPassword);

	Cliente getClientePorMail(String username);

	Cliente registrarAlumnoPersona(Cliente alumno)  throws ErrorValidationException, BusinessException;

}

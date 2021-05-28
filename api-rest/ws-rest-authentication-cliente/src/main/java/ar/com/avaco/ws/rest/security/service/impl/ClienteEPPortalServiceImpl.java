/**
 * 
 */
package ar.com.avaco.ws.rest.security.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.sec.exception.NuclearJSecurityException;
import ar.com.avaco.educacion.domain.entities.cliente.Cliente;
import ar.com.avaco.educacion.service.cliente.ClienteService;
import ar.com.avaco.ws.rest.security.ClienteUserDetailsFactory;
import ar.com.avaco.ws.rest.security.dto.UpdatePasswordDTO;
import ar.com.avaco.ws.rest.security.dto.UserAuthorised;
import ar.com.avaco.ws.rest.security.service.ClienteEPPortalService;

@Service("clienteEPService")
public class ClienteEPPortalServiceImpl implements ClienteEPPortalService {

	private ClienteService clienteService;
	
	
	@Resource(name = "clienteService")
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Override
	public void updatePassword(UpdatePasswordDTO updatePassword) {
		String username = updatePassword.getUsername();
		Cliente cliente = clienteService.getClientePorUsername(username);
		if (cliente.isSistemaExterno()) {
			throw new NuclearJSecurityException("Usuarios de sistemas externos no pueden cambiar la contraseña. Deben utilizar la pagina institucional de su empresa.");
		} 
		clienteService.updatePassword(cliente, updatePassword.getPassword(), updatePassword.getNewPassword());		
	}
	
	@Override
	public void sendMissingPassword(String username) {
		Cliente cliente = (Cliente) clienteService.getClientePorUsername(username);
		
		if (cliente == null) {
			cliente = clienteService.getClientePorMail(username);
			if (cliente == null) {
				throw new UsernameNotFoundException("Username " + username + " not found.");
			}
		}
		
		if (cliente.isSistemaExterno()) {
			throw new NuclearJSecurityException("La contraseña no se puede recuperar por este medio. Debe utilizar la pagina institucional de su empresa.");
		} 
		
		clienteService.resetPassword(cliente.getId());
	}
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente cliente = (Cliente) clienteService.getClientePorUsername(username);
		if (cliente == null) {
			throw new UsernameNotFoundException("Username " + username + " not found.");
		}
		UserAuthorised clienteUDDTO = ClienteUserDetailsFactory.create(cliente);
		return clienteUDDTO;
	}

	@Override
	public boolean isSistemaExterno(String username) {
		Cliente cliente = (Cliente) clienteService.getClientePorUsername(username);
		
		if (cliente == null ) {
			return false;
		}
		
		return cliente.isSistemaExterno();
	}

	
}

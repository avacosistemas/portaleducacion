/**
 * 
 */
package ar.com.avaco.ws.rest.security.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.com.avaco.educacion.domain.cliente.Cliente;
import ar.com.avaco.educacion.service.cliente.ClienteService;
import ar.com.avaco.ws.rest.security.ClienteUserDetailsFactory;
import ar.com.avaco.ws.rest.security.dto.UpdatePasswordDTO;
import ar.com.avaco.ws.rest.security.dto.UserAuthorised;
import ar.com.avaco.ws.rest.security.service.ClienteEPService;

@Service("clienteEPService")
public class ClienteEPServiceImpl implements ClienteEPService {

	private ClienteService clienteService;
	
	
	@Resource(name = "clienteService")
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Override
	public void updatePassword(UpdatePasswordDTO updatePassword) {
		String username = updatePassword.getUsername();
		Cliente cliente = clienteService.getClientePorUsername(username);
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

	
}

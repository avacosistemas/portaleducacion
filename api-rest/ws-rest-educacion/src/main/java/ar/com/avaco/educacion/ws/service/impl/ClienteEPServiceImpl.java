package ar.com.avaco.educacion.ws.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.educacion.domain.entities.cliente.Cliente;
import ar.com.avaco.educacion.service.cliente.ClienteService;
import ar.com.avaco.educacion.ws.dto.ClienteCompletoDTO;
import ar.com.avaco.educacion.ws.dto.ClienteListadoDTO;
import ar.com.avaco.educacion.ws.dto.ContactoDTO;
import ar.com.avaco.educacion.ws.service.ClienteEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("clienteEPService")
public class ClienteEPServiceImpl extends CRUDEPBaseService<Long, ClienteListadoDTO, Cliente, ClienteService> implements ClienteEPService {

	@Override
	public ClienteCompletoDTO getClienteCompleto(Long id) {
		Cliente cliente = this.getService().getClienteCompleto(id);
		ClienteCompletoDTO clienteDTO = new ClienteCompletoDTO(cliente);
		return clienteDTO;
	}

	@Override
	public List<ClienteListadoDTO> listClientesListado() {
		List<Cliente> clientes = this.getService().listClientesListado();
		List<ClienteListadoDTO> convertToDtos = convertToDtos(clientes);
		clientes = null;
		return convertToDtos;
	}
		
	@Override
	protected Cliente convertToEntity(ClienteListadoDTO dto) {
		Cliente cliente = new Cliente();
		cliente.setBloqueado(dto.isBloqueado());
		cliente.setEmail(dto.getEmail());
		cliente.setId(dto.getId());
		cliente.setUsername(dto.getUsername());
		return cliente;
	}

	@Override
	protected ClienteListadoDTO convertToDto(Cliente entity) {
		ClienteListadoDTO clienteDTO = new ClienteListadoDTO();
		clienteDTO.setBloqueado(entity.isBloqueado());
		clienteDTO.setEmail(entity.getEmail());
		clienteDTO.setId(entity.getId());
		clienteDTO.setUsername(entity.getUsername());
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fechaIngreso = df.format(entity.getFechaRegistro());
		
		clienteDTO.setFechaIngreso(fechaIngreso);
		clienteDTO.setTelefono(entity.getContacto().getTelefonoMovil());
		clienteDTO.setNombreApellido(entity.getRazonSocialNombreApellido());
		clienteDTO.setTipo(entity.getIdentificacion().getTipo().toString());
		clienteDTO.setNumero(entity.getIdentificacion().getNumero());
		return clienteDTO;
	}

	public ClienteCompletoDTO updateClienteCompleto(Long id, ClienteCompletoDTO clienteDTO) throws BusinessException {
		Cliente cliente = clienteDTO.toEntity();
		cliente.setId(id);
		cliente = service.updateClienteCompleto(cliente);
		return new ClienteCompletoDTO(cliente);
	}

	@Override
	public void validarActualizacionCliente(ClienteCompletoDTO clienteDTO) throws ErrorValidationException, BusinessException {
		this.service.validarActualizacionDatosPersonalesCliente(clienteDTO.toEntity());
	}

	@Override
	public void resetPassword(Long id) {
		service.resetPassword(id);
	}
	
	@Override
	public void validarContacto(ContactoDTO contactoDTO) throws ErrorValidationException {
		this.service.validarContacto(contactoDTO.toEntity());
	}

	@Override
	@Resource(name = "clienteService")
	protected void setService(ClienteService clienteService) {
		this.service = clienteService;
	}
	
}

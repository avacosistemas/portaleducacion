
package ar.com.avaco.educacion.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.educacion.ws.dto.ClienteCompletoDTO;
import ar.com.avaco.educacion.ws.dto.ClienteListadoDTO;
import ar.com.avaco.educacion.ws.dto.ContactoDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface ClienteEPService extends CRUDEPService<Long, ClienteListadoDTO> {

	List<ClienteListadoDTO> listClientesListado();
	
	ClienteCompletoDTO getClienteCompleto(Long id);

	ClienteCompletoDTO updateClienteCompleto(Long id, ClienteCompletoDTO clienteDTO) throws BusinessException;

	void validarActualizacionCliente(ClienteCompletoDTO clienteDTO) throws ErrorValidationException, BusinessException;

	void validarContacto(ContactoDTO contactoDTO) throws ErrorValidationException;

	void resetPassword(Long id);


}

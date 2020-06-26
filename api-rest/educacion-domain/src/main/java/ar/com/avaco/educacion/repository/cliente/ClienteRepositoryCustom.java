package ar.com.avaco.educacion.repository.cliente;

import java.util.List;

import ar.com.avaco.educacion.domain.cliente.Cliente;

public interface ClienteRepositoryCustom {

	Cliente getClienteCompleto(Long id);

	List<Cliente> listClientesListado();
	
}

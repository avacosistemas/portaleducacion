package ar.com.avaco.educacion.repository.cliente;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.cliente.Cliente;

public interface ClienteRepository extends NJRepository<Long, Cliente>, ClienteRepositoryCustom {

	Cliente findByUsernameEquals(String username);

	Cliente findByUsernameEqualsIgnoreCase(String username);

	Cliente findByEmailEqualsIgnoreCase(String email);

	Cliente findByIdentificacionNumeroEqualsIgnoreCase(String numero);

	Cliente findByIdentificacionNumeroLikeIgnoreCase(String numero);

	Cliente findByCuentaBancariaAliasEqualsIgnoreCase(String cbu);

	Cliente findByCuentaBancariaCbuEqualsIgnoreCase(String cbu);

	Cliente findByCuentaBancariaAliasEqualsIgnoreCaseAndIdNot(String cbu, Long id);
	
	Cliente findByCuentaBancariaCbuEqualsIgnoreCaseAndIdNot(String cbu, Long id);

}

package ar.com.avaco.educacion.repository.cliente;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.cliente.Cliente;

/**
 * Repositorio de Clientes
 * @author beto
 *
 */
@Repository("clienteRepository")
public class ClienteRepositoryImpl extends NJBaseRepository<Long, Cliente> implements ClienteRepositoryCustom {

	public ClienteRepositoryImpl(EntityManager entityManager) {
		super(Cliente.class, entityManager);
	}

	@Override
	public Cliente getClienteCompleto(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("identificacion", FetchMode.JOIN); 
		criteria.setFetchMode("cuentaBancaria", FetchMode.JOIN); 
		criteria.setFetchMode("contacto", FetchMode.JOIN); 
		criteria.setFetchMode("ingreso", FetchMode.JOIN); 
		return (Cliente) criteria.uniqueResult();
	}

	@Override
	public List<Cliente> listClientesListado() {
		Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
		criteria.setFetchMode("identificacion", FetchMode.JOIN); 
//		criteria.setFetchMode("cuentaBancaria", FetchMode.JOIN); 
		criteria.setFetchMode("contacto", FetchMode.JOIN); 
//		criteria.setFetchMode("ingreso", FetchMode.JOIN);
		criteria.addOrder(Order.asc("username"));
		return criteria.list();
	}


	
	
}

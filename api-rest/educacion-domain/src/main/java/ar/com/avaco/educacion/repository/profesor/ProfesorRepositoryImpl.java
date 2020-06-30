package ar.com.avaco.educacion.repository.profesor;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Profesor;

@Repository("profesorRepository")
public class ProfesorRepositoryImpl extends NJBaseRepository<Long, Profesor> implements ProfesorRepositoryCustom {

	public ProfesorRepositoryImpl(EntityManager entityManager) {
		super(Profesor.class, entityManager);
	}
	
	@Override
	public Profesor getProfesor(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("identificacion", FetchMode.JOIN); 
		criteria.setFetchMode("contacto", FetchMode.JOIN); 
		return (Profesor) criteria.uniqueResult();
	}
	
	@Override
	public Profesor getMateriaProfesor(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("materias", FetchMode.JOIN); 
		return (Profesor) criteria.uniqueResult();
	}
	

	@Override
	public List<Profesor> listProfesores() {
		Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
		criteria.setFetchMode("identificacion", FetchMode.JOIN); 
		criteria.setFetchMode("contacto", FetchMode.JOIN); 
		criteria.addOrder(Order.asc("username"));
		return criteria.list();
	}

}

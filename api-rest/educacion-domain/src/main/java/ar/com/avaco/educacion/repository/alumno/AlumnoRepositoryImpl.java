package ar.com.avaco.educacion.repository.alumno;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Alumno;

@Repository("alumnoRepository")
public class AlumnoRepositoryImpl extends NJBaseRepository<Long, Alumno> implements AlumnoRepositoryCustom {

	public AlumnoRepositoryImpl(EntityManager entityManager) {
		super(Alumno.class, entityManager);
	}
	
	@Override
	public Alumno getAlumno(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("identificacion", FetchMode.JOIN); 
		criteria.setFetchMode("contacto", FetchMode.JOIN); 
		return (Alumno) criteria.uniqueResult();
	}
	
	@Override
	public List<Alumno> listAlumnos() {
		Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
		criteria.setFetchMode("identificacion", FetchMode.JOIN); 
		criteria.setFetchMode("contacto", FetchMode.JOIN); 
		criteria.addOrder(Order.asc("username"));
		return criteria.list();
	}


}

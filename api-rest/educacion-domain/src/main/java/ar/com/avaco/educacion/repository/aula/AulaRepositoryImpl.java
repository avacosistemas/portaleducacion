package ar.com.avaco.educacion.repository.aula;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.educacion.domain.entities.Aula;

@Repository("aulaRepository")
public class AulaRepositoryImpl extends NJBaseRepository<Long, Aula> implements AulaRepositoryCustom {

	public AulaRepositoryImpl(EntityManager entityManager) {
		super(Aula.class, entityManager);
	}

	@Override
	public Aula getAula(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("materia", FetchMode.JOIN); 
		criteria.setFetchMode("comentarios", FetchMode.JOIN);
		criteria.setFetchMode("profesores", FetchMode.JOIN);
		criteria.setFetchMode("alumnos", FetchMode.JOIN); 
		return (Aula) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aula> listAulas() {
		Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
//		criteria.setFetchMode("materia", FetchMode.JOIN); 
//		criteria.setFetchMode("comentarios", FetchMode.JOIN);
//		criteria.setFetchMode("profesores", FetchMode.JOIN);
//		criteria.setFetchMode("alumnos", FetchMode.JOIN); 
		return criteria.list();
	}
	

}

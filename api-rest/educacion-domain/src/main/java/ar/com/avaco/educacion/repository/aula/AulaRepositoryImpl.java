package ar.com.avaco.educacion.repository.aula;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.w3c.dom.css.CSSUnknownRule;

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
		criteria.setFetchMode("institucion", FetchMode.JOIN); 
		criteria.setFetchMode("comentarios", FetchMode.JOIN);
		criteria.setFetchMode("profesores", FetchMode.JOIN);
		criteria.setFetchMode("alumnos", FetchMode.JOIN); 
		criteria.setFetchMode("eventosAulas", FetchMode.JOIN);
		return (Aula) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aula> listAulas() {
		Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
		criteria.addOrder(Order.desc("dia")).addOrder(Order.desc("hora"));
//		criteria.setFetchMode("materia", FetchMode.JOIN); 
//		criteria.setFetchMode("comentarios", FetchMode.JOIN);
//		criteria.setFetchMode("profesores", FetchMode.JOIN);
//		criteria.setFetchMode("alumnos", FetchMode.JOIN); 		
		return criteria.list();
	}

	@Override
	public List<Aula> listAulasAbiertasByInstitucion(Long idInstitucion) {
		
		Integer hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		Date nowDate = now.getTime();
		
		
		Criteria criteria = getCurrentSession().createCriteria(getHandledClass());
		
		LogicalExpression hoyConHora = Restrictions.and(Restrictions.eq("dia", nowDate), Restrictions.ge("hora", hour));
		criteria.add(Restrictions.or(hoyConHora, Restrictions.gt("dia", nowDate)));
		
		criteria.createAlias("institucion", "institucion");
		criteria.add(Restrictions.eq("institucion.id", idInstitucion));
		
		criteria.addOrder(Order.desc("dia"));
		criteria.addOrder(Order.desc("hora"));
		
		return criteria.list();
		
	}
	

}

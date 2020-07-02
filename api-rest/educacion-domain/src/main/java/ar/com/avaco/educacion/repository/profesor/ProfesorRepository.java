package ar.com.avaco.educacion.repository.profesor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Profesor;

public interface ProfesorRepository extends NJRepository<Long, Profesor>, ProfesorRepositoryCustom {

	  @Query(value = "SELECT p FROM Profesor p JOIN FETCH p.materias WHERE p.id = :id")
	  List<Profesor> selectMateriasProfesor(@Param("id") Long id);
	
}

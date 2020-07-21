package ar.com.avaco.educacion.repository.pregresp;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;

public interface PreguntaRespuestaRepository extends NJRepository<Long, PreguntaRespuesta>, PreguntaRespuestaRepositoryCustom {

	@Query(value = "SELECT pr FROM PreguntaRespuesta pr JOIN FETCH pr.profesor p WHERE p.id = :id")
	List<PreguntaRespuesta> selectAllByProfesorId(@Param("id") Long id);
	
	//List<PreguntaRespuesta> findAllByProfesorId(Long id);
	
}

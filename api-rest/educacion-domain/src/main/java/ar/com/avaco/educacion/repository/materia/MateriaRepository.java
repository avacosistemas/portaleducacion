package ar.com.avaco.educacion.repository.materia;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Materia;

public interface MateriaRepository extends NJRepository<Long, Materia>, MateriaRepositoryCustom {

	
	List<Materia> findAllByNivelId(Integer idNivel);

	List<Materia> findAllByProfesoresId(Long id);
	
	Materia findByDescripcionEqualsIgnoreCaseAndNivelId(String descripcion, Integer idNivel);
	
}

package ar.com.avaco.educacion.repository.aula;

import java.util.List;

import ar.com.avaco.educacion.domain.entities.Aula;

public interface AulaRepositoryCustom {

	Aula getAula(Long id);

	List<Aula> listAulas();

}

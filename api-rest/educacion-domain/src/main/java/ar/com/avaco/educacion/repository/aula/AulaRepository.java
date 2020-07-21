package ar.com.avaco.educacion.repository.aula;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Aula;

public interface AulaRepository extends NJRepository<Long, Aula>, AulaRepositoryCustom {

	
	  @Query(value = "SELECT a.hora FROM Aula a WHERE a.dia = :fecha and a.id = :id")
	  List<LocalTime> selectHorariosByFechaAndProfesor(@Param("fecha") LocalDate fecha, @Param("id") Long id);
	  
	  List<Aula> findAllByProfesoresIdIn(Long idProfesor);
	
}

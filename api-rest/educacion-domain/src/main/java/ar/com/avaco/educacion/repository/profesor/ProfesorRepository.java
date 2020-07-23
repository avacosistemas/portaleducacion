package ar.com.avaco.educacion.repository.profesor;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.Profesor;

public interface ProfesorRepository extends NJRepository<Long, Profesor>, ProfesorRepositoryCustom, JpaSpecificationExecutor<Profesor> {

	  @Query(value = "SELECT p FROM Profesor p JOIN FETCH p.materias WHERE p.id = :id")
	  Profesor findProfesorConMaterias(@Param("id") Long id);

	  @Query(value = "SELECT p.foto FROM Profesor p WHERE p.id = :id")
	  byte[] getFotoPerfil(@Param("id") Long id);
	  
	  @Query(value = "SELECT p FROM Profesor p JOIN FETCH p.materias m WHERE m.id = :idMateria")
	  List<Profesor> findAllCatalogoDocenteByMateria(@Param("idMateria") Long idMateria, Sort orden);

	  @Query(value = "SELECT p FROM Profesor p JOIN FETCH p.materias m WHERE m.nivel.id = :idNivel")
	  List<Profesor> findAllCatalogoDocenteByNivel(@Param("idNivel") Integer idNivel, Sort orden);

	  @Query(value = "SELECT p FROM Profesor p JOIN FETCH p.materias")
	  List<Profesor> findAllProfesoresOrder(Sort orden);
  
}

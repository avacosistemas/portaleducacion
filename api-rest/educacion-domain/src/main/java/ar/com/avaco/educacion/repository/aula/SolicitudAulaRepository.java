package ar.com.avaco.educacion.repository.aula;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.com.avaco.arc.core.component.bean.repository.NJRepository;
import ar.com.avaco.educacion.domain.entities.EstadoSolicitudAula;
import ar.com.avaco.educacion.domain.entities.SolicitudAula;

public interface SolicitudAulaRepository extends NJRepository<Long, SolicitudAula>, SolicitudAulaRepositoryCustom {

	List<SolicitudAula> findAllByEstadoOrderByFechaSolicitudAsc(EstadoSolicitudAula estado);

	@Query("select count(e)>0 from SolicitudAula e where e.alumno.id = :idAlumno and e.aula.id = :idAula")
	boolean existsByAulaIdAndAlumnoId(@Param("idAula") Long idAula, @Param("idAlumno") Long idAlumno);

	List<SolicitudAula> findAllByEstadoNotOrderByFechaSolicitudDesc(EstadoSolicitudAula pendiente);
}

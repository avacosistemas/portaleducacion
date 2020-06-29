package ar.com.avaco.educacion.service.alumno;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.repository.alumno.AlumnoRepository;

@Transactional
@Service("alumnoService")
public class AlumnoServiceImpl extends NJBaseService<Long, Alumno, AlumnoRepository> implements AlumnoService {

	
}

package ar.com.avaco.educacion.service.profesor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.repository.profesor.ProfesorRepository;

@Transactional
@Service("profesorService")
public class ProfesorServiceImpl extends NJBaseService<Long, Profesor, ProfesorRepository> implements ProfesorService {


}

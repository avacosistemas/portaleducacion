package ar.com.avaco.educacion.service.aula;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.repository.aula.AulaRepository;

@Transactional
@Service("aulaService")
public class AulaServiceImpl extends NJBaseService<Long, Aula, AulaRepository> implements AulaService {

	
}

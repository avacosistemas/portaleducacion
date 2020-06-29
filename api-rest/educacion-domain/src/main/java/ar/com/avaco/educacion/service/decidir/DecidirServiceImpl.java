package ar.com.avaco.educacion.service.decidir;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.Decidir;
import ar.com.avaco.educacion.repository.decidir.DecidirRepository;

@Transactional
@Service("decidirService")
public class DecidirServiceImpl extends NJBaseService<Long, Decidir, DecidirRepository> implements DecidirService {

	
}

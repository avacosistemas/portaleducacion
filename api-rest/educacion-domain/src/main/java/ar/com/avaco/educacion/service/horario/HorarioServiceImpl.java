package ar.com.avaco.educacion.service.horario;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.Horario;
import ar.com.avaco.educacion.repository.horario.HorarioRepository;


@Transactional
@Service("horarioService")
public class HorarioServiceImpl extends NJBaseService<Long, Horario, HorarioRepository> implements HorarioService {

	
}

package ar.com.avaco.educacion.service.comentario;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.Comentario;
import ar.com.avaco.educacion.repository.comentario.ComentarioRepository;

@Transactional
@Service("comentarioService")
public class ComentarioServiceImpl extends NJBaseService<Long, Comentario, ComentarioRepository> implements ComentarioService {

	
}

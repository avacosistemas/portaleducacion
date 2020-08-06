package ar.com.avaco.educacion.service.comentario;


import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.educacion.domain.entities.Comentario;

public interface ComentarioService extends NJService<Long, Comentario> {

	List<Comentario> listByClaseId(Long idClase);

	
}

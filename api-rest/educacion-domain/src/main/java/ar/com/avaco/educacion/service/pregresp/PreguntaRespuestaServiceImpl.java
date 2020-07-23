package ar.com.avaco.educacion.service.pregresp;


import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.repository.pregresp.PreguntaRespuestaRepository;

@Transactional
@Service("preguntaRespuestaService")
public class PreguntaRespuestaServiceImpl extends NJBaseService<Long, PreguntaRespuesta, PreguntaRespuestaRepository> implements PreguntaRespuestaService {

	@Resource(name = "preguntaRespuestaRepository")
	void setPreguntaRespuestaRepository(PreguntaRespuestaRepository preguntaRtaRepository) {
		this.repository = preguntaRtaRepository;
	}
	
}

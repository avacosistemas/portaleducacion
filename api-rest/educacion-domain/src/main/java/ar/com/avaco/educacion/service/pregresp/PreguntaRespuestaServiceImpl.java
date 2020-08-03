package ar.com.avaco.educacion.service.pregresp;


import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.PreguntaRespuesta;
import ar.com.avaco.educacion.repository.pregresp.PreguntaRespuestaRepository;

@Transactional
@Service("preguntaRespuestaService")
public class PreguntaRespuestaServiceImpl extends NJBaseService<Long, PreguntaRespuesta, PreguntaRespuestaRepository> implements PreguntaRespuestaService {

	@Override
	public List<PreguntaRespuesta> listByProfesor(Long idProfesor){
		return this.repository.selectAllByProfesorId(idProfesor);
	}
	
	@Resource(name = "preguntaRespuestaRepository")
	void setPreguntaRespuestaRepository(PreguntaRespuestaRepository preguntaRtaRepository) {
		this.repository = preguntaRtaRepository;
	}
	
}

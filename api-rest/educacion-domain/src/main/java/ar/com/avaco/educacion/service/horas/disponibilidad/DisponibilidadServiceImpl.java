package ar.com.avaco.educacion.service.horas.disponibilidad;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.Disponibilidad;
import ar.com.avaco.educacion.repository.horas.disponibilidad.DisponibilidadRepository;

@Transactional
@Service("disponibilidadService")
public class DisponibilidadServiceImpl extends NJBaseService<Long, Disponibilidad, DisponibilidadRepository> implements DisponibilidadService {

	
}

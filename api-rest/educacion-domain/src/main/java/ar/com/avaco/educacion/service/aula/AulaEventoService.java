package ar.com.avaco.educacion.service.aula;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.AulaEventos;

public interface AulaEventoService extends NJService<Long, AulaEventos> {

	AulaEventos saveEvento(AulaEventos aulaEvento) throws BusinessException;

}

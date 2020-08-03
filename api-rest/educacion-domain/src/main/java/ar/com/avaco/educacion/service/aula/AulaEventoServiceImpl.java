package ar.com.avaco.educacion.service.aula;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.AulaEventos;
import ar.com.avaco.educacion.domain.entities.cliente.Cliente;
import ar.com.avaco.educacion.repository.aula.AulaEventoRepository;
import ar.com.avaco.educacion.service.cliente.ClienteService;

@Transactional
@Service("aulaEventoService")
public class AulaEventoServiceImpl extends NJBaseService<Long, AulaEventos, AulaEventoRepository>
		implements AulaEventoService {

	@Autowired
	private ClienteService clienteService; 

	@Autowired
	private AulaService aulaService;

	@Override
	public AulaEventos saveEvento(AulaEventos aulaEventos) throws BusinessException {

		Aula aula = this.aulaService.get(aulaEventos.getAula().getId());
		if (aula == null) {
			throw new BusinessException("No existe el aula");
		}
		
		Cliente cliente = this.clienteService.get(aulaEventos.getUsuario().getId());
		if (cliente == null)
			throw new BusinessException("El usuario id " + aulaEventos.getUsuario().getId() + " no existe");


		
		return super.save(aulaEventos);
	}

	@Resource(name = "aulaEventoRepository")
	public void setRepository(AulaEventoRepository aulaAlumnoRepository) {
		this.repository = aulaAlumnoRepository;
	}

	@Resource(name = "aulaService")
	public void setAulaService(AulaService aulaService) {
		this.aulaService = aulaService;
	}

}

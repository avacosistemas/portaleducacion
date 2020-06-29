package ar.com.avaco.educacion.service.horas.compra;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.Compra;
import ar.com.avaco.educacion.repository.horas.compra.CompraRepository;

@Transactional
@Service("compraService")
public class CompraServiceImpl extends NJBaseService<Long, Compra, CompraRepository> implements CompraService {

	
}

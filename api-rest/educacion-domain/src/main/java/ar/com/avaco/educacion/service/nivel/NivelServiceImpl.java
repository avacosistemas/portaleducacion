package ar.com.avaco.educacion.service.nivel;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.educacion.domain.entities.Nivel;
import ar.com.avaco.educacion.repository.nivel.NivelRepository;

@Transactional
@Service("nivelService")
public class NivelServiceImpl extends NJBaseService<Integer, Nivel, NivelRepository> implements NivelService {

	
	/**
	 * @see NivelService#createNivel(Nivel)
	 */
	@Override
	public Nivel createNivel(Nivel entity) throws BusinessException {

		validateNivelNoEmpty(entity);
		validateNivelOnSave(entity);

		Nivel nivel = new Nivel();
		nivel.setDescripcion(entity.getDescripcion());

		nivel = this.getRepository().save(nivel);

		return nivel;
	}
	
	/**
	 * @see NivelService#updateNivel(Nivel)
	 */
	@Override
	public Nivel updateNivel(Nivel entity) throws BusinessException {

		validateNivelNoEmpty(entity);
		validateNivelOnSave(entity);

		Nivel nivel = this.get(entity.getId());
		nivel.setId(entity.getId());
		nivel.setDescripcion(entity.getDescripcion());

		nivel = this.getRepository().save(nivel);

		return nivel;
	}
	
	/**
	 * Valida que el nivel no sea nulo
	 * 
	 * @param nivel nivel a validar
	 * @throws BusinessException error de negocio
	 */
	private void validateNivelNoEmpty(Nivel nivel) throws BusinessException {
		if (nivel == null) {
			throw new BusinessException("Nivel vacío.");
		}
	}

	/**
	 * Valida que el nivel no se encuentre registrada.
	 * 
	 * @param nivel a validar.
	 */
	public void validateNivelOnSave(Nivel nivel) throws ErrorValidationException, BusinessException {

		Map<String, String> errores = new HashMap<String, String>();

		// Validacion descripcion
		if (StringUtils.isBlank(nivel.getDescripcion())) {
			errores.put("nivel", "El campo nivel es requerido.");
		} else if (getRepository().findByDescripcionEqualsIgnoreCase(nivel.getDescripcion()) != null) {
			errores.put("nivel", "El nivel no esta disponible. Intente otro diferente.");
		}

		if (!errores.isEmpty()) {
			throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}

	}
	
	@Resource(name = "nivelRepository")
	void setNivelRepository(NivelRepository nivelRepository) {
		this.repository = nivelRepository;
	}
	
}

package ar.com.avaco.educacion.service.materia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Nivel;
import ar.com.avaco.educacion.repository.materia.MateriaRepository;
import ar.com.avaco.educacion.service.nivel.NivelService;

@Transactional
@Service("materiaService")
public class MateriaServiceImpl extends NJBaseService<Long, Materia, MateriaRepository> implements MateriaService {
	
	@Autowired
	private NivelService nivelService;
	
	/**
	 * @see MateriaService#listByNivel(Integer)
	 */
	@Override
	public List<Materia> listByNivel(Integer idNivel) {
		return getRepository().findAllByNivelId(idNivel);
	}
	
	/**
	 * @see MateriaService#listByProfesor(Long)
	 */
	@Override
	public List<Materia> listByProfesor(Long idProfesor) {
		return getRepository().findAllByProfesoresId(idProfesor);
	}
	
	/**
	 * @see MateriaService#createMateria(Materia)
	 */
	@Override
	public Materia createMateria(Materia entity) throws BusinessException {

		validateMateriaNoEmpty(entity);
		validateMateriaOnSave(entity);
		
		Nivel nivel = nivelService.get(entity.getNivel().getId());
		
		Materia materia = new Materia();
		materia.setDescripcion(entity.getDescripcion());
		materia.setNivel(nivel);

		materia = this.getRepository().save(materia);
		return materia;
	}
	
	/**
	 * @see MateriaService#updateMateria(Materia)
	 */
	@Override
	public Materia updateMateria(Materia entity) throws BusinessException {

		validateMateriaNoEmpty(entity);
		validateMateriaOnSave(entity);

		Materia materia = this.get(entity.getId());
		materia.setId(entity.getId());
		materia.setDescripcion(entity.getDescripcion());
		
		materia.setNivel(nivelService.get(entity.getNivel().getId()));		

		materia = this.getRepository().save(materia);

		return materia;
	}
	
	/**
	 * Valida que la materia no sea null
	 * 
	 * @param materia
	 * @throws BusinessException
	 */
	private void validateMateriaNoEmpty(Materia materia) throws BusinessException {
		if (materia == null) {
			throw new BusinessException("Materia vacía.");
		} else if (materia.getNivel().getId() == null) {
			throw new BusinessException("Nivel vacío.");
		}
	}

	/**
	 * Valida que la materia no se encuentre registrada.
	 * 
	 * @param materia a validar.
	 */
	public void validateMateriaOnSave(Materia materia) throws ErrorValidationException, BusinessException {

		Map<String, String> errores = new HashMap<String, String>();
	
		// Validacion descripcion
		if (StringUtils.isBlank(materia.getDescripcion())) {
			errores.put("descripcion", "El campo descripcion es requerido.");
		} else if (getRepository().findByDescripcionEqualsIgnoreCaseAndNivelId(materia.getDescripcion(), materia.getNivel().getId()) != null) {
			errores.put("descripcion", "La combinación de velores Nivel/Descripción no puede ser duplicada. Intente otro diferente.");
		}

		if (!errores.isEmpty()) {
			throw new ErrorValidationException("Se encontraron los siguientes errores", errores);
		}

	}
	
	@Resource(name = "materiaRepository")
	void setMateriaRepository(MateriaRepository materiaRepository) {
		this.repository = materiaRepository;
	}

	
}

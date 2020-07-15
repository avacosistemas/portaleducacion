package ar.com.avaco.educacion.service.alumno;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.repository.alumno.AlumnoRepository;
import ar.com.avaco.educacion.service.cliente.ClienteService;


@Transactional
@Service("alumnoService")
public class AlumnoServiceImpl extends NJBaseService<Long, Alumno, AlumnoRepository> implements AlumnoService {

	private ClienteService clienteService;
	
	@Autowired
	public AlumnoServiceImpl(ClienteService clienteService) {

		this.clienteService = clienteService;
	}

	/**
	 * @see AlumnoService#getAlumno(Long)
	 */
	@Override
	public Alumno getAlumno(Long id) {
		return getRepository().getAlumno(id);
	}
	
	/**
	 * @see AlumnoService#listProfesores()
	 */
	@Override
	public List<Alumno> listAlumnos() {
		return getRepository().listAlumnos();
	}

	/**
	 * @see AlumnoService#createAlumno(Alumno)
	 */
	@Override
	public Alumno createAlumno(Alumno alumno) throws BusinessException {
		Alumno newAlumno = (Alumno) clienteService.registrarClientePersona(alumno);
		return newAlumno;
	}
	
	/**
	 * @see AlumnoService#updateAlumno(Alumno)
	 */
	@Override
	public Alumno updateAlumno(Alumno entity) throws BusinessException {
		Alumno alumno = (Alumno) clienteService.updateProfesorAlumno(entity);
		return alumno;
	}

	/**
	 * @see AlumnoService#bloquearHabilitarAlumno(Alumno, boolean)
	 */
	@Override
	public Alumno bloquearHabilitarAlumno(Alumno entity, boolean bloquear) throws BusinessException {
		Alumno alumno = this.get(entity.getId());
		alumno.setId(entity.getId());
		alumno.setBloqueado(bloquear);
		
		alumno = this.getRepository().save(alumno);

		return alumno;
	}

	@Resource(name = "alumnoRepository")
	void setAlumnoRepository(AlumnoRepository alumnoRepository) {
		this.repository = alumnoRepository;
	}
	
}

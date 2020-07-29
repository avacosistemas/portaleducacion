package ar.com.avaco.educacion.service.alumno;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Institucion;
import ar.com.avaco.educacion.repository.alumno.AlumnoRepository;
import ar.com.avaco.educacion.service.cliente.ClienteService;
import ar.com.avaco.educacion.service.institucion.InstitucionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Service("alumnoService")
public class AlumnoServiceImpl extends NJBaseService<Long, Alumno, AlumnoRepository> implements AlumnoService {

	private ClienteService clienteService;
	private InstitucionService institucionService;
	
	@Autowired
	public AlumnoServiceImpl(ClienteService clienteService, InstitucionService institucionService) {

		this.clienteService = clienteService;
		this.institucionService = institucionService;
	}

	/**
	 * @see AlumnoService#getAlumno(Long)
	 */
	@Override
	public Alumno getAlumno(Long id) {
		return getRepository().getAlumno(id);
	}
	
	/**
	 * @see AlumnoService#listAlumnos()
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
		Institucion institucion = institucionService.get(alumno.getInstitucion().getId());
		alumno.setInstitucion(institucion);
		Alumno newAlumno = (Alumno) clienteService.registrarClientePersona(alumno);
		return newAlumno;
	}
	
	/**
	 * @see AlumnoService#updateAlumno(Alumno)
	 */
	@Override
	public Alumno updateAlumno(Alumno entity) throws BusinessException {

		Alumno alumno = (Alumno) this.clienteService.validaUpdateProfesorAlumno(entity);
		Institucion institucion = institucionService.get(entity.getInstitucion().getId());
		alumno.setInstitucion(institucion);
		return this.getRepository().save(alumno);
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

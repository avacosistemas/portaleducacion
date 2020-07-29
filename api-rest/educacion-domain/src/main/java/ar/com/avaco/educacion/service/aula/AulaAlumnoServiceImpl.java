package ar.com.avaco.educacion.service.aula;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.AulaAlumno;
import ar.com.avaco.educacion.repository.aula.AulaAlumnoRepository;
import ar.com.avaco.educacion.service.SolapaUtils;
import ar.com.avaco.educacion.service.alumno.AlumnoService;

@Transactional
@Service("aulaAlumnoService")
public class AulaAlumnoServiceImpl extends NJBaseService<Long, AulaAlumno, AulaAlumnoRepository>
		implements AulaAlumnoService {

	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private AulaService aulaService;

	@Override
	public List<AulaAlumno> listByAula(Long idAula) {
		return this.getRepository().findByAulaId(idAula);
	}

	@Override
	public AulaAlumno saveAlumno(AulaAlumno aulaAlumno) throws BusinessException {

		Aula aula = this.aulaService.get(aulaAlumno.getAula().getId());
		Alumno alumno = this.alumnoService.get(aulaAlumno.getAlumno().getId());

		if (alumno == null)
			throw new BusinessException("El alumno id " + aulaAlumno.getAlumno().getId() + " no existe");

		if (aula.getInstitucion() != null && !alumno.getInstitucion().getId().equals(aula.getInstitucion().getId()))
			throw new BusinessException("El alumno no pertenece a la Institucion del aula");

		
		Iterator<AulaAlumno> iter = aula.getAlumnos().iterator();
		boolean found = false;
		while (iter.hasNext() && !found) {
			AulaAlumno aa = iter.next();
			if (aa.getAlumno().getId().equals(alumno.getId())) {
				throw new BusinessException("El aula ya tiene asociado al alumno");
			}
		}
		
		List<AulaAlumno> aulasAlumno = this.getRepository().findByAlumnoId(alumno.getId());
		
		if (aulasAlumno != null && !alumno.getAulas().isEmpty()) {
			for (AulaAlumno aa : aulasAlumno) {
				if (SolapaUtils.seSolapanAulas(aa.getAula(), aula, 60)) {
					throw new BusinessException(
							"El alumno ya tiene una clase en ese dia y horario. Ver Aula para Materia: "
									+ aa.getAula().getMateria().getDescripcion());
				}
			}
		}

		return super.save(aulaAlumno);
	}

	@Resource(name = "aulaAlumnoRepository")
	public void setRepository(AulaAlumnoRepository aulaAlumnoRepository) {
		this.repository = aulaAlumnoRepository;
	}

	@Resource(name = "aulaService")
	public void setAulaService(AulaService aulaService) {
		this.aulaService = aulaService;
	}

	@Resource(name = "alumnoService")
	public void setAlumnoService(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}

}

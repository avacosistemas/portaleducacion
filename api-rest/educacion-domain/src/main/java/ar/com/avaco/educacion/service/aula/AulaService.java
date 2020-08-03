package ar.com.avaco.educacion.service.aula;

import java.util.Date;
import java.util.List;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Aula;

public interface AulaService extends NJService<Long, Aula> {

	/**
	 * Retorna el aula segun su id
	 * 
	 * @param id
	 * @return
	 */
	Aula getAula(Long id);
	
	/** 
	 * Retorna un listado de aulas
	 * 
	 * @return
	 */
	List<Aula> getAulas();
	
	/**
	 * Crea un aula
	 * 
	 * @param aula
	 * @return
	 * @throws BusinessException 
	 */
	Aula crearAula(Aula aula) throws BusinessException;

	/**
	 * Actualiza los datos del aula
	 * 
	 * @param aula
	 * @return
	 * @throws BusinessException 
	 */
	Aula updateAula(Aula aula) throws BusinessException;

	/**
	 * Agrega un profesor al aula
	 * 
	 * @param idAula
	 * @param idProfesor
	 * @return
	 * @throws BusinessException 
	 */
	Aula addProfesorAula(Long idAula, Long idProfesor) throws BusinessException;

	
	/**
	 * Quita un profesor de un aula
	 * 
	 * @param idAula
	 * @param idProfesor
	 * @throws BusinessException
	 */
	void removeAulaProfesor(Long idAula, Long idProfesor) throws BusinessException;

//	/**
//	 * Agrega un alumno al aula
//	 * 
//	 * @param idAula
//	 * @param idAlumno
//	 * @return
//	 * @throws BusinessException 
//	 */
//	Aula addAlumnoAula(Long idAula, Long idAlumno) throws BusinessException;

	
//	/**
//	 * Quita un alumno del aula
//	 * 
//	 * @param idAula
//	 * @param idAlumno
//	 */
//	void removeAulaAlumno(Long idAula, Long idAlumno)  throws BusinessException;
	
	
	/**
	 * Alumno compra una clase
	 * 
	 * @param idAlumno
	 * @param idProfesor
	 * @param idMateria
	 * @param dia
	 * @param hora
	 * @return
	 */
	Aula comprarClase(Long idAlumno, Long idProfesor, Long idMateria, Date dia, String hora) throws BusinessException;

	/**
	 * Crea la Clase virtual en la plataforma de meeting y retorna el URL para el join
	 * 
	 * @param aula
	 * @param idProfesor
	 * @return
	 */
	String abrirClase(Aula aula, Long idProfesor) throws BusinessException;

	/**
	 * Valida el origen del request
	 * @param fromIP
	 */
	void validarEventoClase(String fromIP)  throws BusinessException;

	/**
	 * Permite a los alumnos unirse a una Clase Virtual existente.
	 * 
	 * @param aula
	 * @param idAlumno
	 * @return
	 * @throws BusinessException
	 */
	String unirseClase(Aula aula, String idAlumno) throws BusinessException;
	 
}

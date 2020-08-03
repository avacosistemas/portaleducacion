package ar.com.avaco.educacion.service.aulaVirtual;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.aulaVirtual.Clase;
import ar.com.avaco.educacion.exception.AulaVirtualException;

public interface AulaVirtualService extends NJService<Long, Aula> {

	/**
	 * Retorna True si la clase con idClase se encuentra abierta
	 * 
	 * @param idClase
	 * @return
	 */
	Boolean isClaseAbierta(String idClase);

	
	/**
	 * Realiza la union del alumno a la clase identificada por el idClase
	 * 
	 * @param idClase
	 * @param alumno
	 * @return
	 * @throws AulaVirtualException
	 */
	String unirseAlumnoClase(String idClase, Alumno alumno) throws AulaVirtualException;
	
	
	/**
	 * Realiza la union de un profesor adicional a la clase identificada por el idClase
	 * 
	 * @param idClase
	 * @param profesor
	 * @return
	 * @throws AulaVirtualException
	 */
	String unirseProfesorClase(String idClase, Profesor profesor) throws AulaVirtualException;

	/**
	 * Crea y abre la clase segun Materia (obtenida de Aula) y el profesor que la dicta
	 * 
	 * @param profesor
	 * @param aula
	 * @return
	 * @throws AulaVirtualException
	 */
	Clase crearClase(Profesor profesor, Aula aula) throws AulaVirtualException;


	/**
	 * Valida el origen del request de evento
	 * 
	 * @param fromIP
	 * @throws AulaVirtualException 
	 */
	void validarOrigenEvento(String fromIP) throws AulaVirtualException;


	
}

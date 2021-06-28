package ar.com.avaco.educacion.service.notificacion;

import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.aulaVirtual.Clase;
import ar.com.avaco.educacion.domain.entities.cliente.Cliente;

public interface NotificacionService {

	void notificarResetoPassword(Cliente cliente, String tmppass);

	void notificarRegistroClienteNuevoPassword(Cliente cliente, String tmpass);

	void notificarAlumnoNuevo(Cliente cliente);
	
	void notificarHabilitacionExitosa(Cliente cliente);
	
	void notificarAprobacionDocenteCategoria(Cliente cliente, String categoria);
	
	void notificarNuevaPregunta(Cliente cliente, String url);
	
	void notificarCompraClase(Cliente cliente, Clase clase, Aula aula);
	
	void notificarPagoClase(Cliente cliente, Clase clase, Aula aula);
	
	void notificarNuevaCalificacion(Cliente profesor, Cliente alumno, String calificacion, String comentario);
	
	void notificarAsignacionProfesorAula(Cliente profesor, Aula aula);
	
	void notificarAsignacionAlumnoAula(Cliente alumno, Aula aula);

	void notificarActualizacionAula(Aula entity);

	void notificarCambioProfesor(Profesor profesorAnterior, Aula entity);
	
	void notificarInicioServer();

	void notificarRechazoUnionAula(Aula aula, Alumno alumno, String motivo);

	void notificarSolicitudUnion(Aula aula, Alumno alumno);

}

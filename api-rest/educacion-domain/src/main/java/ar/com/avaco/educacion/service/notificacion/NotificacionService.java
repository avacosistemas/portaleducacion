package ar.com.avaco.educacion.service.notificacion;

import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.aulaVirtual.Clase;
import ar.com.avaco.educacion.domain.entities.cliente.Cliente;

public interface NotificacionService {

	void notificarResetoPassword(Cliente cliente, String tmppass);

	void notificarRegistroClienteNuevoPassword(Cliente cliente, String tmpass);

	void notificarAlumnoNuevo(Cliente cliente);
	
	void notificarHabilitacionExitosa(Cliente cliente);
	
	void notificarAprobacionDocenteCategoria(Cliente cliente, String categoria);
	
	void notificarNuevaPregunta(Cliente cliente, String url);
	
	void notificarCompraClase(Cliente cliente, Clase clase);
	
	void notificarPagoClase(Cliente cliente, Clase clase );
	
	void notificarNuevaCalificacion(Cliente profesor, Cliente alumno, String calificacion, String Comentario);
	
	void notificarAsignacionProfesorAula(Cliente profesor, Aula aula);
	
	void notificarAsignacionAlumnoAula(Cliente alumno, Aula aula);
	

}

package ar.com.avaco.educacion.service.notificacion;

import ar.com.avaco.educacion.domain.cliente.Cliente;

public interface NotificacionService {

	void notificarResetoPassword(Cliente cliente, String tmppass);

	void notificarRegistroClienteNuevoPassword(Cliente cliente, String tmpass);

}

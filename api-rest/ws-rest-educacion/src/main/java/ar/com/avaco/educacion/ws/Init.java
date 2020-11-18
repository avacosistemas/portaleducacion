package ar.com.avaco.educacion.ws;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Component;

import ar.com.avaco.educacion.service.notificacion.NotificacionService;

/**
 * Servlet implementation class Init
 */
@Component("starter")
public class Init {
	
	private NotificacionService notificacionService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public Init() {
        super();    
    }
    
	public void iniciar() throws Exception {
    	notificacionService.notificarInicioServer();
	}


	public NotificacionService getNotificacionService() {
		return notificacionService;
	}

	@Resource(name = "notificacionService")
	public void setNotificacionService(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}
	
	

}

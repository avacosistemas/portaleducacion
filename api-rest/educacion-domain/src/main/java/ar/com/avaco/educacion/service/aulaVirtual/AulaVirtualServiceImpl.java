package ar.com.avaco.educacion.service.aulaVirtual;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMException;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.aulaVirtual.Clase;
import ar.com.avaco.educacion.domain.entities.cliente.Cliente;
import ar.com.avaco.educacion.exception.AulaVirtualException;
import ar.com.avaco.educacion.repository.aula.AulaRepository;

@Service("aulaVirtualService")
public class AulaVirtualServiceImpl extends NJBaseService<Long, Aula, AulaRepository> implements AulaVirtualService {

	@Value("bbb.api.url.callback.evento")
	private String callbackEvento;

	@Value("bbb.url")
	private String url;

	@Value("bbb.ip")
	private String ip;

	@Value("bbb.salt")
	private String salt;

	@Value("bbb.welcome.message")
	private String welcomeMessage;

	private BigBlueButtonApi api;

	public AulaVirtualServiceImpl() {
		super();
		api = new BigBlueButtonApi();
		api.setSalt(salt);
		api.setBigBlueButtonIP(ip);
		api.setBigBlueButtonURL(url);
		api.setUrlEventsCallBack(callbackEvento);
	}

	@Override
	public Clase crearClase(Profesor profesor, Aula aula) throws AulaVirtualException {
		String idMeeting;
		try {
			idMeeting = api.createMeeting(aula.generatedIdAula(profesor), welcomeMessage, null, null, null, null, null);
			String urlSala = api.getJoinMeetingURL(profesor.getUsername(), idMeeting, "mp", null, profesor.getId());

			// Hook a los eventos
			api.hookEvents(aula.getId(), idMeeting);

			Clase clase = new Clase();
			clase.setIdClase(idMeeting);
			clase.setUrl(urlSala);
			return clase;
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (AulaVirtualException e) {
			e.printStackTrace();
		}
		throw new ServiceException("Error desconocido");
	}

	@Override
	public String unirseAlumnoClase(String idClase, Alumno alumno) throws AulaVirtualException {
		return unirseClase(idClase, alumno, "ap");
	}

	@Override
	public String unirseProfesorClase(String idClase, Profesor profesor) throws AulaVirtualException {
		return unirseClase(idClase, profesor, "mp");
	}

	protected String unirseClase(String idClase, Cliente cliente, String password) throws AulaVirtualException {
		return api.getJoinMeetingURL(cliente.getNombreApellido(), idClase, password, null, cliente.getId());
	}

	@Override
	public void validarOrigenEvento(String fromIP) throws AulaVirtualException {
		if (!fromIP.equals(api.getBigBlueButtonIP())) {
			throw new AulaVirtualException(1, "El evento no proviene del servidor de AulaVirtual");
		}
	}

	@Override
	public Boolean isClaseAbierta(String idClase) {
		String result = api.isMeetingRunning(idClase);
		if (result != null && result.trim().equalsIgnoreCase("TRUE")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public BigBlueButtonApi getApi() {
		return api;
	}

	public void setApi(BigBlueButtonApi api) {
		this.api = api;
	}

	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

}

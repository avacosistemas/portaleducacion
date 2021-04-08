package ar.com.avaco.educacion.ws.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.AulaEventos;
import ar.com.avaco.educacion.domain.entities.TipoEventoAula;
import ar.com.avaco.educacion.domain.entities.TipoUsuario;
import ar.com.avaco.educacion.domain.entities.cliente.Cliente;
import ar.com.avaco.educacion.service.aula.AulaEventoService;
import ar.com.avaco.educacion.service.aula.AulaService;
import ar.com.avaco.educacion.ws.dto.AulaAlumnoDTO;
import ar.com.avaco.educacion.ws.dto.AulaDTO;
import ar.com.avaco.educacion.ws.dto.AulaListadoDTO;
import ar.com.avaco.educacion.ws.dto.AulaProfesorDTO;
import ar.com.avaco.educacion.ws.dto.CompraAulaDTO;
import ar.com.avaco.educacion.ws.dto.EventoBBBDTO;
import ar.com.avaco.educacion.ws.dto.EventoClaseDTO;
import ar.com.avaco.educacion.ws.dto.eventData.EventDataBBBDTO;
import ar.com.avaco.educacion.ws.service.AulaEPService;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.security.util.ClienteUtils;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("aulaEPService")
public class AulaEPServiceImpl extends CRUDEPBaseService<Long, AulaDTO, Aula, AulaService> implements AulaEPService {

	@Autowired
	private AulaEventoService aulaEventoService;
	
	@Override
	protected AulaDTO convertToDto(Aula aula) {
		AulaDTO aulaDTO = new AulaDTO(aula);
		return aulaDTO;
	}

	@Override
	protected Aula convertToEntity(AulaDTO aulaDTO) {
		return aulaDTO.toEntity();
	}

	@Override
	@Resource(name = "aulaService")
	protected void setService(AulaService aulaService) {
		this.service = aulaService;
	}
	
	@Resource(name = "aulaEventoService")
	protected void setService(AulaEventoService aulaEventoService) {
		this.aulaEventoService = aulaEventoService;
	}


	@Override
	public List<AulaListadoDTO> listAulas() {
		List<Aula> aulas = this.service.getAulas();
		List<AulaListadoDTO> aulasDTOS = new ArrayList<>();
		for (Aula aula : aulas) {
			aulasDTOS.add(new AulaListadoDTO(aula));

		}
		return aulasDTOS;
	}

	@Override
	public AulaDTO getAula(Long id) {
		Aula aula = this.getService().getAula(id);
		AulaDTO aulaDTO = new AulaDTO(aula);
		return aulaDTO;
	}

	@Override
	public AulaDTO crearAula(AulaDTO aulaDTO) throws BusinessException {
		Aula aula = convertToEntity(aulaDTO);
		aula = service.crearAula(aula);
		return new AulaDTO(aula);
	}

	@Override
	public AulaDTO updateAula(Long id, AulaDTO aulaDTO) throws BusinessException {
		Aula aula = convertToEntity(aulaDTO);
		aula.setId(id);
		aula = service.updateAula(aula);
		return new AulaDTO(aula);
	}

	@Override
	public AulaDTO comprarClase(CompraAulaDTO compraAulaDTO) throws BusinessException {

		Long idAlumno = ClienteUtils.getClienteLogueadoId();

		// FIXME BORRAR
		if (idAlumno == null) {
			idAlumno = new Long(5);
		}

		Aula aula;
		try {
			aula = service.comprarClase(idAlumno, compraAulaDTO.getIdProfesor(), compraAulaDTO.getIdMateria(),
					DateUtils.toDate(compraAulaDTO.getDia()), compraAulaDTO.getHora());
		} catch (ParseException e) {
			throw new BusinessException("Dia invalido");
		}

		return new AulaDTO(aula);
	}

	@Override
	public void registrarEventoClase(Long idAula, EventoClaseDTO eventoClaseDTO) throws BusinessException {

		service.validarEventoClase(eventoClaseDTO.getFromIP());
		mappearEvento(eventoClaseDTO);

		TipoEventoAula tipoEvento = getTipoEvento(eventoClaseDTO);
		if (tipoEvento == null) // Ningun Evento a ser capturado
			return;

		if (tipoEvento == TipoEventoAula.FIN_CLASE) {
			// TODO MatarHook
			return;
		}

		Aula aula=new Aula();
		aula.setId(idAula);
		
		TipoUsuario tipoUsuario = getTipoUsuario(eventoClaseDTO);
		if (tipoUsuario == null)
			throw new BusinessException("Tipo Usuario no encontrado");

		Cliente cliente=new Cliente();
		cliente.setId(Long.parseLong(eventoClaseDTO.getMappedEvent().getData().getAttributes().getUser().getId()));
		
		AulaEventos aulaEventos = new AulaEventos();
		aulaEventos.setAula(aula);
		aulaEventos.setTipoEvento(tipoEvento);
		aulaEventos.setTipoUsuario(tipoUsuario);
		aulaEventos.setUsuario(cliente);
		aulaEventos.setFecha(new Date(eventoClaseDTO.getTimestamp()));

		aulaEventoService.saveEvento(aulaEventos);

	}

	private void mappearEvento(EventoClaseDTO eventoClaseDTO) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			String datas=eventoClaseDTO.getEvent().substring(1, eventoClaseDTO.getEvent().length());
			EventDataBBBDTO eventData = mapper.readValue(datas,
					EventDataBBBDTO.class);
			eventoClaseDTO.setMappedEvent(eventData);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private TipoEventoAula getTipoEvento(EventoClaseDTO eventoClaseDTO) {
		String evento=eventoClaseDTO.getMappedEvent().getData().getId();
		if (evento==null)
			return null;
		
		if (evento.equals("user-joined")) {
			return TipoEventoAula.INGRESO;
		} else if (evento.equals("user-left")) {
			return TipoEventoAula.EGRESO;
		} else if (evento.equals("meeting-ended"))
			return TipoEventoAula.FIN_CLASE;
		return null;
	}

	private TipoUsuario getTipoUsuario(EventoClaseDTO eventoClaseDTO) {
		String tipo=eventoClaseDTO.getMappedEvent().getData().getAttributes().getUser().getRole();
		if (tipo==null)
			return null;
		
		if (tipo.equals("MODERATOR")) {
			return TipoUsuario.PROFESOR;
		} else if (tipo.equals("ATTENDEE") || tipo.equals("VIEWER") ) {
			return TipoUsuario.ALUMNO;
		} 
		return null;
	}

	@Override
	public List<EventoBBBDTO> listarEventos() {
		List<AulaEventos> eventos = this.aulaEventoService.list();
		List<EventoBBBDTO> eventosDTO = new ArrayList<>();
		eventos.stream().sorted(Comparator.comparing(AulaEventos::getFecha)).forEach(x -> eventosDTO.add(new EventoBBBDTO(x)));;
		return eventosDTO;
	}
	
}

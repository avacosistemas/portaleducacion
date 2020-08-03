package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.ws.dto.eventData.EventDataBBBDTO;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class EventoClaseDTO extends DTOEntity<Long> {

	private Long id;

	private String event;

	private Long timestamp;

	private String fromIP;
	
	private EventDataBBBDTO mappedEvent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getFromIP() {
		return fromIP;
	}

	public void setFromIP(String fromIP) {
		this.fromIP = fromIP;
	}

	public EventDataBBBDTO getMappedEvent() {
		return mappedEvent;
	}

	public void setMappedEvent(EventDataBBBDTO mappedEvent) {
		this.mappedEvent = mappedEvent;
	}

	
	// event={"data":{"type":"event","attributes":{},"event":{"ts":0}}}

}

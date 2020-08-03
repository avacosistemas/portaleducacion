package ar.com.avaco.educacion.ws.dto.eventData;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class EventDataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4815840579486471998L;

	@JsonProperty("type")
	public String type;

	@JsonProperty("id")
	public String id;

	// @JsonProperty("attributes")
	public EventDataAttributesDTO attributes;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EventDataAttributesDTO getAttributes() {
		return attributes;
	}

	public void setAttributes(EventDataAttributesDTO attributes) {
		this.attributes = attributes;
	}

}

package ar.com.avaco.educacion.ws.dto.eventData;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventDataBBBDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3614762164385591766L;
	@JsonProperty("data")
	public EventDataDTO data;

	public EventDataBBBDTO() {
		super();
	}

	public EventDataDTO getData() {
		return data;
	}

	public void setData(EventDataDTO data) {
		this.data = data;
	}

}

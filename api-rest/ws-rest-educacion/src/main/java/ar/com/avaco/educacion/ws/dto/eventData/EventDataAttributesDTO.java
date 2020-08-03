package ar.com.avaco.educacion.ws.dto.eventData;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class EventDataAttributesDTO implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 2993621170233127052L;
	
	@JsonProperty("user")
	public EventDataUserDTO user;

	public EventDataUserDTO getUser() {
		return user;
	}

	public void setUser(EventDataUserDTO user) {
		this.user = user;
	}
	
	

}

package ar.com.avaco.educacion.ws.dto.eventData;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class EventDataUserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7642731542082440160L;

	@JsonProperty("external-user-id")
	public String id;

	@JsonProperty("role")
	public String role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

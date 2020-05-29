package ar.com.avaco.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

/**
 * @author avaco
 *
 */
public class ContactUSDTO extends DTOEntity<Long> {

	private Long id;

	private String name;
	
	private String telephone;
	
	private String email;

	private String message;
	
	private String date;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}

/**
 * 
 */
package ar.com.avaco.ws.rest.security.dto;

import java.io.Serializable;

import ar.com.avaco.arc.core.domain.Entity;

/**
 * @author Claudio
 *
 */
public class ClienteDTO extends Entity<Long> implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3712576319173757829L;
	private Long id;
	private String username;
	private String nombreApellido;
	private String tipoPerfil;
	private String email;
	private boolean bloqueado;
	
	public ClienteDTO() {
		
	}
	

	public ClienteDTO(Long id, String username, String nombreApellido, String lastname, String tipoPerfil, String email,
			boolean bloqueado) {
		super();
		this.id = id;
		this.username = username;
		this.nombreApellido = nombreApellido;
		this.tipoPerfil = tipoPerfil;
		this.email = email;
		this.bloqueado = bloqueado;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNombreApellido() {
		return nombreApellido;
	}


	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}


	public String getTipoPerfil() {
		return tipoPerfil;
	}


	public void setTipoPerfil(String tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isBloqueado() {
		return bloqueado;
	}


	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	
		
}

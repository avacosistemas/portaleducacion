package ar.com.avaco.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;

import ar.com.avaco.arc.sec.domain.UserDetailsExtended;
import ar.com.avaco.ws.rest.security.dto.UserAuthorised;

/**
 * 
 */
public class ClienteUserDetailsDTO extends UserAuthorised implements UserDetailsExtended {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4647415856914178636L;
	private final String username;
	private final String nombreApellido;
	private final String password;
	private final String email;
	private final boolean bloqueado;
	private final Date lastPasswordResetDate;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private Long id;

	public ClienteUserDetailsDTO(Long id, String username, String nombreApellido, String email, String password,
			Collection<? extends GrantedAuthority> authorities, boolean bloqueado, Date lastPasswordResetDate, 
			boolean aaccountNonExpired, boolean aaccountNonLocked, boolean acredentialsNonExpired) {
		this.setId(id);
		this.username = username;
		this.nombreApellido = nombreApellido;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.bloqueado = bloqueado;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.accountNonExpired = aaccountNonExpired;
		this.accountNonLocked= aaccountNonLocked ;
		this.credentialsNonExpired = acredentialsNonExpired;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public boolean isEnabled() {
		return !bloqueado;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	@Override
	public Date getFechaAltaPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBloqueado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer getIntentosFallidosLogin() {
		// TODO Auto-generated method stub
		return null;
	}

}
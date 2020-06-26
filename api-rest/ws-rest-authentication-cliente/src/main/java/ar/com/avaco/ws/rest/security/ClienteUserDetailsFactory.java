package ar.com.avaco.ws.rest.security;

import ar.com.avaco.model.ClienteUserDetailsDTO;
import ar.com.avaco.educacion.domain.cliente.Cliente;

public final class ClienteUserDetailsFactory {

    private ClienteUserDetailsFactory() {
    }

    public static ClienteUserDetailsDTO create(Cliente cliente) {
        return new ClienteUserDetailsDTO(
                cliente.getId(),
                cliente.getUsername(),
                cliente.getRazonSocialNombreApellido(),
                cliente.getEmail(),
                cliente.getPassword(),
                cliente.getAuthorities(),
                cliente.isBloqueado(),
                cliente.getFechaAltaPassword(),
                cliente.isAccountNonExpired(),
                cliente.isAccountNonLocked(),
                cliente.isCredentialsNonExpired()
        );
    }

}
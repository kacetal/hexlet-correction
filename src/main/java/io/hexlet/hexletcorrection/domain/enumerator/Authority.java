package io.hexlet.hexletcorrection.domain.enumerator;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    ROLE_USER,
    ROLE_ADMIN,
    ROLE_CORRECTER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}

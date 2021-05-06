package io.hexlet.hexletcorrection.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public interface SecuredAccount extends UserDetails {

    @Override
    String getUsername();

    @Override
    String getPassword();

    @Override
    boolean isEnabled();

    @Override
    Collection<? extends GrantedAuthority> getAuthorities();

    @Override
    default boolean isAccountNonExpired() {
        return true;
    }

    @Override
    default boolean isAccountNonLocked() {
        return true;
    }

    @Override
    default boolean isCredentialsNonExpired() {
        return true;
    }
}

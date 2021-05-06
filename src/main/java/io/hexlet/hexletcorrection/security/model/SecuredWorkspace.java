package io.hexlet.hexletcorrection.security.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public interface SecuredWorkspace extends UserDetails {

    @Override
    @Value("#{target.name}")
    String getUsername();

    @Override
    @Value("#{target.token}")
    String getPassword();

    @Override
    default Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "WORKSPACE");
    }

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

    @Override
    default boolean isEnabled() {
        return true;
    }
}

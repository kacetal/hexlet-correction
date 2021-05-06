package io.hexlet.hexletcorrection.config;

import io.hexlet.hexletcorrection.security.service.SecuredAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.*;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecuredAccountService securedAccountService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return securedAccountService;
    }
}

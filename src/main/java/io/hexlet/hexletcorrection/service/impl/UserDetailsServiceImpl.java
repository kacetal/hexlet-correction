package io.hexlet.hexletcorrection.service.impl;

import io.hexlet.hexletcorrection.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountService
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(format("User with username=''{0}'' not found!", username)));
    }
}

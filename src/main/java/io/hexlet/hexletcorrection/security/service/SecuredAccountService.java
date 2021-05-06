package io.hexlet.hexletcorrection.security.service;

import io.hexlet.hexletcorrection.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecuredAccountService implements UserDetailsService {

    private final AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getSecuredAccountByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Secured Account with username='" + username + "' not found"));
    }
}

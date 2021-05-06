package io.hexlet.hexletcorrection.repository;

import io.hexlet.hexletcorrection.domain.Account;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AccountRepositoryIT {

    @Autowired
    private AccountRepository repository;

    public static Stream<Account> getTestAccount() {
        return Stream.of(new Account()
            .setEmail("email@email.com")
            .setUsername("username"));
    }

    @ParameterizedTest
    @MethodSource("getTestAccount")
    void whenSavedThenFindsById(final Account account) {
        final var id = repository.saveAndFlush(account).getId();
        assertThat(id).isNotNull().isNotEmpty().isNotBlank();
        assertThat(repository.findById(id)).isPresent().get().hasFieldOrPropertyWithValue("id", id);
    }

    @ParameterizedTest
    @MethodSource("getTestAccount")
    void whenSavedThenCreatedDateIsBeforeNow(final Account account) {
        repository.saveAndFlush(account);
        assertThat(account.getCreatedDate()).isBefore(LocalDateTime.now());
    }

    @ParameterizedTest
    @MethodSource("getTestAccount")
    void whenSavedThenCreatedDateIsEqualsToModifiedDate(final Account account) {
        repository.saveAndFlush(account);
        assertThat(account.getCreatedDate()).isEqualTo(account.getModifiedDate());
    }

    @ParameterizedTest
    @MethodSource("getTestAccount")
    void whenUpdatedThenCreatedDateIsNotEqualsToModifiedDate(final Account account) {
        repository.saveAndFlush(account);
        repository.saveAndFlush(account.setFirstName("Updated"));
        assertThat(account.getCreatedDate()).isNotEqualTo(account.getModifiedDate());
    }

    @ParameterizedTest
    @MethodSource("getTestAccount")
    void whenUpdatedThenCreatedDateIsBeforeToModifiedDate(final Account account) {
        repository.saveAndFlush(account);
        repository.saveAndFlush(account.setFirstName("Updated"));
        assertThat(account.getCreatedDate()).isBefore(account.getModifiedDate());
    }
}

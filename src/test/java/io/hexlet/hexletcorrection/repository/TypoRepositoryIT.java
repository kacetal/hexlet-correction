package io.hexlet.hexletcorrection.repository;

import io.hexlet.hexletcorrection.domain.Typo;
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
class TypoRepositoryIT {

    @Autowired
    private TypoRepository repository;

    public static Stream<Typo> getTestTypo() {
        return Stream.of(new Typo()
            .setPageUrl("url")
            .setReporterName("reporter name")
            .setTextTypo("text typo"));
    }

    @ParameterizedTest
    @MethodSource("getTestTypo")
    void whenSavedThenFindsById(final Typo typo) {
        final var id = repository.saveAndFlush(typo).getId();
        assertThat(id).isNotNull().isPositive();
        assertThat(repository.findById(id)).isPresent().get().hasFieldOrPropertyWithValue("id", id);
    }

    @ParameterizedTest
    @MethodSource("getTestTypo")
    void whenSavedThenCreatedDateIsBeforeNow(final Typo typo) {
        repository.saveAndFlush(typo);
        assertThat(typo.getCreatedDate()).isBefore(LocalDateTime.now());
    }

    @ParameterizedTest
    @MethodSource("getTestTypo")
    void whenSavedThenCreatedDateIsEqualsToModifiedDate(final Typo typo) {
        repository.saveAndFlush(typo);
        assertThat(typo.getCreatedDate()).isEqualTo(typo.getModifiedDate());
    }

    @ParameterizedTest
    @MethodSource("getTestTypo")
    void whenUpdatedThenCreatedDateIsNotEqualsToModifiedDate(final Typo typo) {
        repository.saveAndFlush(typo);
        repository.saveAndFlush(typo.setTextTypo("Updated"));
        assertThat(typo.getCreatedDate()).isNotEqualTo(typo.getModifiedDate());
    }

    @ParameterizedTest
    @MethodSource("getTestTypo")
    void whenUpdatedThenCreatedDateIsBeforeToModifiedDate(final Typo typo) {
        repository.saveAndFlush(typo);
        repository.saveAndFlush(typo.setTextTypo("Updated"));
        assertThat(typo.getCreatedDate()).isBefore(typo.getModifiedDate());
    }
}

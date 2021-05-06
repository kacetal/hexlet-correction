package io.hexlet.hexletcorrection.repository;

import io.hexlet.hexletcorrection.domain.Comment;
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
class CommentRepositoryIT {

    @Autowired
    private CommentRepository repository;

    public static Stream<Comment> getTestComment() {
        return Stream.of(new Comment().setMessage("message"));
    }

    @ParameterizedTest
    @MethodSource("getTestComment")
    void whenSavedThenFindsById(final Comment comment) {
        final var id = repository.saveAndFlush(comment).getId();
        assertThat(id).isNotNull().isPositive();
        assertThat(repository.findById(id)).isPresent().get().hasFieldOrPropertyWithValue("id", id);
    }

    @ParameterizedTest
    @MethodSource("getTestComment")
    void whenSavedThenCreatedDateIsBeforeNow(final Comment comment) {
        repository.saveAndFlush(comment);
        assertThat(comment.getCreatedDate()).isBefore(LocalDateTime.now());
    }

    @ParameterizedTest
    @MethodSource("getTestComment")
    void whenSavedThenCreatedDateIsEqualsToModifiedDate(final Comment comment) {
        repository.saveAndFlush(comment);
        assertThat(comment.getCreatedDate()).isEqualTo(comment.getModifiedDate());
    }

    @ParameterizedTest
    @MethodSource("getTestComment")
    void whenUpdatedThenCreatedDateIsNotEqualsToModifiedDate(final Comment comment) {
        repository.saveAndFlush(comment);
        repository.saveAndFlush(comment.setMessage("Updated"));
        assertThat(comment.getCreatedDate()).isNotEqualTo(comment.getModifiedDate());
    }

    @ParameterizedTest
    @MethodSource("getTestComment")
    void whenUpdatedThenCreatedDateIsBeforeToModifiedDate(final Comment comment) {
        repository.saveAndFlush(comment);
        repository.saveAndFlush(comment.setMessage("Updated"));
        assertThat(comment.getCreatedDate()).isBefore(comment.getModifiedDate());
    }
}

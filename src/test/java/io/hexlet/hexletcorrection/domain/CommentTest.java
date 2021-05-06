package io.hexlet.hexletcorrection.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@JsonTest
class CommentTest {

    @Autowired
    private ObjectMapper objectMapper;

    public static Stream<Comment> getTestComment() {
        final var comment1 = new Comment().setId(1L);
        final var comment2 = new Comment().setId(2L);
        final var typo1 = new Typo().setId(1L).addComment(comment1);
        final var typo2 = new Typo().setId(2L).addComment(comment2);
        final var account1 = new Account().setId("ID1").addTypo(typo1).addComment(comment1);
        final var account2 = new Account().setId("ID2").addTypo(typo2).addComment(comment2);
        final var workspace = new Workspace().setId("ID")
            .addAccount(account1).addAccount(account2)
            .addTypo(typo1).addTypo(typo2);
        return Stream.of(comment1, comment2);
    }

    @ParameterizedTest
    @MethodSource("getTestComment")
    public void isNotRecursionCallForJackson(final Comment comment) {
        assertDoesNotThrow(() -> objectMapper.writeValueAsString(comment));
    }

    @ParameterizedTest
    @MethodSource("getTestComment")
    public void isNotRecursionCallForToString(final Comment comment) {
        assertDoesNotThrow(comment::toString);
    }

    @Test
    public void equalsIfIdsEquals() {
        assertThat(new Comment().setId(1L)).isEqualTo(new Comment().setId(1L));
    }

    @Test
    public void notEqualsIfIdsNotEquals() {
        assertThat(new Comment().setId(1L)).isNotEqualTo(new Comment().setId(2L));
    }

    @Test
    public void notEqualsIfOneIdNull() {
        assertThat(new Comment().setId(1L)).isNotEqualTo(new Comment());
    }
}

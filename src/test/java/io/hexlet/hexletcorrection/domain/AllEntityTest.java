package io.hexlet.hexletcorrection.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class AllEntityTest {

    /**
     * Verifies the equals/hashcode contract on the domain object.
     */
    @ParameterizedTest
    @ValueSource(classes = {Typo.class, Account.class, Comment.class, Workspace.class})
    public <T> void equalsVerifier(Class<T> clazz) throws Exception {
        T domainObjectOne = clazz.getConstructor().newInstance();
        assertThat(domainObjectOne.toString()).isNotNull();
        assertThat(domainObjectOne).isEqualTo(domainObjectOne);
        assertThat(domainObjectOne.hashCode()).isEqualTo(domainObjectOne.hashCode());
        // Test with an instance of another class
        Object testOtherObject = new Object();
        assertThat(domainObjectOne).isNotEqualTo(testOtherObject);
        assertThat(domainObjectOne).isNotEqualTo(null);
        // Test with an instance of the same class
        T domainObjectTwo = clazz.getConstructor().newInstance();
        assertThat(domainObjectOne).isNotEqualTo(domainObjectTwo);
        // HashCodes are equals because the objects are not persisted yet
        assertThat(domainObjectOne.hashCode()).isEqualTo(domainObjectTwo.hashCode());
    }
}

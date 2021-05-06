package io.hexlet.hexletcorrection.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

/**
 * A Comment.
 */
@Data
@ToString(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Comment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", allocationSize = 5)
    @ToString.Include
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String message;

    @ManyToOne(fetch = LAZY)
    private Account account;

    @ManyToOne(fetch = LAZY)
    private Typo typo;

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || id != null && obj instanceof Comment other && id.equals(other.id);
    }
}

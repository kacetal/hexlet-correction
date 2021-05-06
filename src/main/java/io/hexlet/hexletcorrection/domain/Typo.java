package io.hexlet.hexletcorrection.domain;

import com.fasterxml.jackson.annotation.*;
import io.hexlet.hexletcorrection.domain.enumerator.TypoStatus;
import lombok.*;
import lombok.experimental.Accessors;
import sun.jvm.hotspot.debugger.cdbg.EnumType;

import java.io.Serializable;
import java.util.*;

@Data
@ToString(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Typo extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @ToString.Include
    private final TypoStatus typoStatus = TypoStatus.REPORTED;

    @OneToMany(mappedBy = "typo", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Comment> comments = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "typo_seq")
    @SequenceGenerator(name = "typo_seq", sequenceName = "typo_seq", allocationSize = 3)
    @ToString.Include
    private Long id;

    @NotBlank
    private String pageUrl;

    @NotBlank
    @Size(max = 50)
    private String reporterName;

    @Size(max = 1000)
    private String reporterRemark;

    @Size(max = 100)
    private String textBeforeTypo;

    @NotNull
    @Size(max = 1000)
    private String textTypo;

    @Size(max = 100)
    private String textAfterTypo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Workspace workspace;

    public Typo addComment(final Comment comment) {
        comments.add(comment);
        comment.setTypo(this);
        return this;
    }

    public Typo removeComment(final Comment comment) {
        comments.remove(comment);
        comment.setTypo(null);
        return this;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || id != null && obj instanceof Typo other && id.equals(other.id);
    }
}

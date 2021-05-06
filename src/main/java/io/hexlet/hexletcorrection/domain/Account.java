package io.hexlet.hexletcorrection.domain;

import io.hexlet.hexletcorrection.domain.enumerator.Authority;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import sun.jvm.hotspot.debugger.cdbg.EnumType;

import java.io.Serializable;
import java.util.*;

/**
 * A user account.
 */
@Data
@ToString(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Account extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String avatarUrl = EntityConstants.DEFAULT_AVATAR;

    private final Boolean enabled = true;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private final Set<Authority> authorities = Set.of(Authority.ROLE_USER);

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("account")
    private final Set<Typo> typos = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("account")
    private final Set<Comment> comments = new HashSet<>();

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ToString.Include
    private String id;

    @Email
    @Column(unique = true)
    @ToString.Include
    private String email;

    @Size(min = 2, max = 50)
    @Pattern(regexp = EntityConstants.USERNAME_PATTERN)
    @Column(unique = true)
    @ToString.Include
    private String username;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("accounts")
    private Workspace workspace;

    public Account addTypo(final Typo typo) {
        this.typos.add(typo);
        typo.setAccount(this);
        return this;
    }

    public Account removeTypo(final Typo typo) {
        this.typos.remove(typo);
        typo.setAccount(null);
        return this;
    }

    public Account addComment(final Comment comment) {
        comments.add(comment);
        comment.setAccount(this);
        return this;
    }

    public Account removeComment(final Comment comment) {
        comments.remove(comment);
        comment.setAccount(null);
        return this;
    }

    public Account setEmail(String email) {
        this.email = email == null ? null : email.strip().toLowerCase(Locale.ENGLISH);
        return this;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || id != null && obj instanceof Account other && id.equals(other.id);
    }
}

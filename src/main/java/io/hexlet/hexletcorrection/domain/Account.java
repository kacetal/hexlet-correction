package io.hexlet.hexletcorrection.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.INVALID_EMAIL;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.MAX_ACCOUNT_NAME;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.NOT_EMPTY;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    @NotBlank(message = "Login " + NOT_EMPTY)
    @Size(message = "Username size must be between {0} and {1} characters", min = 2, max = MAX_ACCOUNT_NAME)
    private String username;

    @Column(name = "last_name")
    @Size(message = "Last name not be more than " + MAX_ACCOUNT_NAME + " characters", max = MAX_ACCOUNT_NAME)
    private String lastName;

    @Column(name = "first_name")
    @Size(message = "First name not be more than " + MAX_ACCOUNT_NAME + " characters", max = MAX_ACCOUNT_NAME)
    private String firstName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email " + NOT_EMPTY)
    @Email(message = INVALID_EMAIL)
    private String email;

    @OneToMany(mappedBy = "correcter", fetch = EAGER)
    @Column(name = "corrections_in_progress")
    @JsonIgnoreProperties({"correcter", "resolver"})
    private Set<Correction> correctionsInProgress = new HashSet<>();

    @OneToMany(mappedBy = "resolver", fetch = EAGER)
    @Column(name = "corrections_resolved")
    @JsonIgnoreProperties({"correcter", "resolver"})
    private Set<Correction> correctionsResolved = new HashSet<>();

    @Column(nullable = false)
    @NotBlank(message = "Password " + NOT_EMPTY)
    @Size(message = "Password size must be between {0} and {1} characters", min = 6, max = 20)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

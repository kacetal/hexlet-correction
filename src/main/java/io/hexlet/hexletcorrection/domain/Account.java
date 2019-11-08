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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_EMAIL_ERROR_INVALID;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_FIRST_NAME_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_FIRST_NAME_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_LAST_NAME_ERROR_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_LAST_NAME_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_PASSWORD_ERROR_LENGTH_MIN;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_PASSWORD_LENGTH_MIN;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_USERNAME_ERROR_BLANK;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_USERNAME_ERROR_LENGTH_SIZE;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_USERNAME_LENGTH_MAX;
import static io.hexlet.hexletcorrection.domain.EntityConstrainConstants.ACCOUNT_USERNAME_LENGTH_MIN;
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

    @Column(nullable = false, updatable = false, unique = true, length = ACCOUNT_USERNAME_LENGTH_MAX)
    @NotBlank(message = ACCOUNT_USERNAME_ERROR_BLANK)
    @Size(message = ACCOUNT_USERNAME_ERROR_LENGTH_SIZE,
        min = ACCOUNT_USERNAME_LENGTH_MIN,
        max = ACCOUNT_USERNAME_LENGTH_MAX)
    private String username;

    @Column(name = "last_name", length = ACCOUNT_LAST_NAME_LENGTH_MAX)
    @Max(message = ACCOUNT_LAST_NAME_ERROR_LENGTH_MAX, value = ACCOUNT_LAST_NAME_LENGTH_MAX)
    private String lastName;

    @Column(name = "first_name", length = ACCOUNT_FIRST_NAME_LENGTH_MAX)
    @Max(message = ACCOUNT_FIRST_NAME_ERROR_LENGTH_MAX, value = ACCOUNT_FIRST_NAME_LENGTH_MAX)
    private String firstName;

    @Column(nullable = false, unique = true)
    @Email(message = ACCOUNT_EMAIL_ERROR_INVALID)
    private String email;

    @Column(nullable = false)
    @Min(message = ACCOUNT_PASSWORD_ERROR_LENGTH_MIN, value = ACCOUNT_PASSWORD_LENGTH_MIN)
    private String password;

    @OneToMany(mappedBy = "correcter", fetch = EAGER)
    @Column(name = "corrections_in_progress")
    @JsonIgnoreProperties({"correcter", "resolver"})
    private Set<Correction> correctionsInProgress = new HashSet<>();

    @OneToMany(mappedBy = "resolver", fetch = EAGER)
    @Column(name = "corrections_resolved")
    @JsonIgnoreProperties({"correcter", "resolver"})
    private Set<Correction> correctionsResolved = new HashSet<>();

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

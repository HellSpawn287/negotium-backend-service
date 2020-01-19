package com.negotium.negotiumapp.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email_address")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    @Size(max = 32, message = "{com.negotium.negotiumapp.model.user.User.username.Size}")
    @NotNull(message = "{com.negotium.negotiumapp.model.user.User.username.NotEmpty}")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    @NotNull(message = "{com.negotium.negotiumapp.model.user.User.password.NotEmpty}")
    @Size(min = 8, message = "{com.negotium.negotiumapp.model.user.User.password.Size}")
    private String password;

    @Column(name = "email_address", unique = true, nullable = false)
    @Email(message = "{com.negotium.negotiumapp.model.user.User.email.Email}")
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id_role")}
    )
    private Set<UserRole> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String password, String email, @NotNull AuthProvider provider, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
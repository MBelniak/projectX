package com.projectX.projectX.domain;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Validated
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NaturalId
    @Column(nullable = false, unique = true)
    @NotNull(message = "Please fill in 'email' field.")
    @Email(message = "Email is invalid")
    private String email;
    @NotNull(message = "Please fill in 'password' field.")
    @Length(min = 8, message = "Password has to be at least 8 char. long.")
    private String hash_password;
    @NotNull(message = "Please fill in 'first name' field.")
    @Pattern(regexp = "[\\p{L}]+$")
    private String first_name;
    @NotNull(message = "Please fill in 'surname' field.")
    @Pattern(regexp = "[\\p{L}]+$")
    private String surname;
    @ManyToOne
    private Role role;
    @ManyToMany(mappedBy = "invitedUsers")
    private Set<Party> attended_parties;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    @JsonCreator
    public User(@JsonProperty("hash_password") String hash_password,@JsonProperty("first_name") String first_name,
                @JsonProperty("surname") String surname, @JsonProperty("email") String email) {
        this.hash_password = hash_password;
        this.first_name = first_name;
        this.surname = surname;
        this.email = email;
        this.attended_parties = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash_password() {
        return hash_password;
    }

    public void setHash_password(String hash_password) {
        this.hash_password = hash_password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Party> getAttended_parties() {
        return attended_parties;
    }

    public void setAttended_parties(Set<Party> attended_parties) {
        this.attended_parties = attended_parties;
    }
}

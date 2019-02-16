package com.projectX.projectX.domain;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NaturalId
    private String email;
    private String hash_password;
    private String first_name;
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
    public User(@JsonProperty("hash_password") String hash_password,@JsonProperty("first_name") String firstName,
                @JsonProperty("surname") String surname, @JsonProperty("email") String email, @JsonProperty("role") String role) {
        this.hash_password = hash_password;
        this.first_name = first_name;
        this.surname = surname;
        this.email = email;
        this.attended_parties = new HashSet<>();
        this.role = new Role(role);
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

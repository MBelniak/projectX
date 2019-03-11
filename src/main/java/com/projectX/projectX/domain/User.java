package com.projectX.projectX.domain;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectX.projectX.util.CustomItemSerializer;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Validated
@JsonSerialize(using = CustomItemSerializer.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Pattern(regexp = "[\\p{L}]+$", message = "First name must only match literals")
    private String first_name;
    @NotNull(message = "Please fill in 'surname' field.")
    @Pattern(regexp = "[\\p{L}]+$", message = "Surname must only match literals")
    private String surname;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Please fill in 'date_of_birth' field.")
    private Date date_of_birth;
    @ManyToOne
    private Role role;
    @ManyToMany(mappedBy = "invitedUsers")
    private Set<Party> attended_parties;

    public User() {
    }

    @JsonCreator
    public User(@JsonProperty("password") String hash_password, @JsonProperty("first_name") String first_name,
                @JsonProperty("surname") String surname, @JsonProperty("email") String email, @JsonProperty("date_of_birth") String date_of_birth) {
        this.hash_password = hash_password;
        this.first_name = first_name;
        this.surname = surname;
        this.email = email;
        this.attended_parties = new HashSet<>();
        try {
            this.date_of_birth = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_birth);
        } catch (ParseException e) {
            this.date_of_birth = null;
        }
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

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Set<Party> getAttended_parties() {
        return attended_parties;
    }

    public void setAttended_parties(Set<Party> attended_parties) {
        this.attended_parties = attended_parties;
    }
}

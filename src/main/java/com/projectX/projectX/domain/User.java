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
    private String userName;
    private String hashPassword;
    private String firstName;
    private String surname;
    private String email;
    @ManyToMany(mappedBy = "invitedUsers")
    private Set<Party> attendedParties;

    public User(String userName) {
        this.userName = userName;
    }

    @JsonCreator
    public User(@JsonProperty("userName") String userName, @JsonProperty("password") String hashPassword,@JsonProperty("firstName") String firstName,
                @JsonProperty("surname") String surname, @JsonProperty("email") String email) {
        this.userName = userName;
        this.hashPassword = hashPassword;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.attendedParties = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Set<Party> getAttendedParties() {
        return attendedParties;
    }

    public void setAttendedParties(Set<Party> attendedParties) {
        this.attendedParties = attendedParties;
    }
}

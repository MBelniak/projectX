package com.projectX.projectX.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserPOJO {
    @NotNull(message = "Please fill in 'first name' field.")
    @Pattern(regexp = "[\\p{L}]+$", message = "First name must only match literals")
    private final String first_name;
    @NotNull(message = "Please fill in 'surname' field.")
    @Pattern(regexp = "[\\p{L}]+$", message = "Surname must only match literals")
    private final String surname;
    @NotNull(message = "Please fill in 'email' field.")
    @Email(message = "Email is invalid")
    private final String email;
    @NotNull(message = "Please fill in 'date_of_birth' field.")
    private Date date_of_birth;

    @JsonCreator
    public UserPOJO(@JsonProperty("first_name") String first_name, @JsonProperty("surname") String surname, @JsonProperty("email") String email, @JsonProperty("date_of_birth") String date_of_birth) {
        this.first_name = first_name;
        this.surname = surname;
        this.email = email;
        try {
            this.date_of_birth = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_birth);
        } catch (ParseException e) {
            this.date_of_birth = null;
        }
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }
}

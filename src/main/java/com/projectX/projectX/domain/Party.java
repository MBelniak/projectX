package com.projectX.projectX.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Party implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String city;
    private String address;
    @OneToOne
    private Image image;

    public Party() {}

    @JsonCreator
    public Party(@JsonProperty("name") String name,@JsonProperty("description") String description,@JsonProperty("date") String date,
                 @JsonProperty("city") String city,@JsonProperty("address") String address, @JsonProperty("imageName") String imageName) {
        this.name = name;
        this.description = description;
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            this.date = null;
        }
        this.city = city;
        this.address = address;
        if(imageName!=null) {
            this.image = new Image(imageName);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
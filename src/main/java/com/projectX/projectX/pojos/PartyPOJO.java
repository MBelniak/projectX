package com.projectX.projectX.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PartyPOJO {
    private final String name;
    private final String description;
    private final String date;
    private final String time;
    private final String city;
    private final String address;
    private final String imageName;

    @JsonCreator
    public PartyPOJO(@JsonProperty("name") String name, @JsonProperty("description") String description, @JsonProperty("date") String date,
                     @JsonProperty("time") String time, @JsonProperty("city") String city, @JsonProperty("address") String address,
                     @JsonProperty("imageName") String imageName) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.city = city;
        this.address = address;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getImageName() {
        return imageName;
    }
}

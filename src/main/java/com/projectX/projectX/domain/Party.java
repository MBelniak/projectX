package com.projectX.projectX.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Party implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIME)
    private Date time;
    private String city;
    private String address;
    @OneToOne
    private Image image;
    @ManyToOne
    private User organizer;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name="PARTY_USER",
            joinColumns = @JoinColumn(name="PARTY_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="USER_ID", referencedColumnName = "id")
    )
    private Set<User> invitedUsers;

    public Party() {}

    @JsonCreator
    public Party(@JsonProperty("name") String name,@JsonProperty("description") String description,@JsonProperty("date") String date,
                 @JsonProperty("time") String time,@JsonProperty("city") String city,@JsonProperty("address") String address,
                 @JsonProperty("imageName") String imageName, @JsonProperty("organizer") String organizer)
    {
        this.name = name;
        this.description = description;
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            this.date = null;
        }
        try {
            this.time = new SimpleDateFormat("HH:mm").parse(time);
        } catch (ParseException e) {
            this.time = null;
        }
        this.city = city;
        this.address = address;
        if(imageName!=null) {
            this.image = new Image(imageName);
        }
        else
            this.image = null;
        if(organizer!=null) {
            this.organizer = new User(organizer);
        }
        else
            this.organizer = null;
        this.invitedUsers = new HashSet<>();
    }

    public void addInvitedUser(User user)
    {
        invitedUsers.add(user);
        user.getAttended_parties().add(this);
    }

    public void deleteInvitedUser(User user)
    {
        invitedUsers.remove(user);
        user.getAttended_parties().remove(this);
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Set<User> getInvitedUsers() {
        return invitedUsers;
    }

    public void setInvitedUsers(Set<User> invitedUsers) {
        this.invitedUsers = invitedUsers;
    }
}
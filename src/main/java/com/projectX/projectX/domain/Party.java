package com.projectX.projectX.domain;

import com.projectX.projectX.pojos.PartyPOJO;

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
    private boolean priv;
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

    public Party(PartyPOJO party)
    {
        this.name = party.getName();
        this.description = party.getDescription();
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(party.getDate());
        } catch (ParseException e) {
            this.date = null;
        }
        try {
            this.time = new SimpleDateFormat("HH:mm").parse(party.getTime());
        } catch (ParseException e) {
            this.time = null;
        }
        this.city = party.getCity();
        this.address = party.getAddress();
        this.invitedUsers = new HashSet<>();
        this.priv = party.isPriv();
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
        addInvitedUser(organizer);
    }

    public Set<User> getInvitedUsers() {
        return new HashSet<>(invitedUsers);
    }

    public void addInvitedUsers(Set<User> users) {
        users.forEach(this::addInvitedUser);
    }

    public boolean isPriv() {
        return priv;
    }

    public void setPriv(boolean priv) {
        this.priv = priv;
    }

    public void setInvitedUsers(Set<User> invitedUsers) {
        this.invitedUsers.clear();
        invitedUsers.forEach(this::addInvitedUser);
    }
}
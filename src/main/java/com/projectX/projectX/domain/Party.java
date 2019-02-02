package com.projectX.projectX.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Party {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Date date;
    private boolean partyPrivate;

    protected Party() {}

    public Party(String name, String description, Date date, boolean partyPrivate) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.partyPrivate = partyPrivate;
    }
}
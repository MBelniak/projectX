package com.projectX.projectX.service;

import com.projectX.projectX.domain.Party;

import java.util.Date;

public interface PartyService {
    Iterable<Party> getAll();
    Party create(String name, String description, Date date, boolean partyPrivate);
}

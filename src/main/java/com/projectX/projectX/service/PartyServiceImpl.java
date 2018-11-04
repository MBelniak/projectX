package com.projectX.projectX.service;

import com.projectX.projectX.domain.Party;
import com.projectX.projectX.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PartyServiceImpl implements PartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Override
    public Iterable<Party> getAll() {
        return null;
    }

    @Override
    public Party create(String name, String description, Date date) {
        return null;
    }
}

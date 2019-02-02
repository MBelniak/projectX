package com.projectX.projectX.service;

import com.projectX.projectX.domain.Party;
import com.projectX.projectX.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PartyServiceImpl implements PartyService {

    private final PartyRepository partyRepository;

    @Autowired
    public PartyServiceImpl(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @Override
    public Iterable<Party> getAll() {
        return partyRepository.findAll();
    }

    @Override
    public Party create(String name, String description, Date date, boolean partyPrivate) {
        return partyRepository.save(new Party(name, description, date, partyPrivate));
    }
}

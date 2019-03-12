package com.projectX.projectX.service;

import com.projectX.projectX.domain.Party;
import com.projectX.projectX.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartyServiceImpl implements PartyService {

    private final PartyRepository partyRepository;

    @Autowired
    public PartyServiceImpl(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @Override
    public List<Party> getAll()
    {
        List<Party> parties = new ArrayList<>();
        partyRepository.findAll().forEach(parties::add);
        return parties;
    }

    @Override
    public Party getParty(Long id)
    {
        return partyRepository.findById(id).orElse(null);
    }

    @Override
    public Party saveParty(Party party) {
        return partyRepository.save(party);
    }

    @Override
    public Party updateParty(Party party, Long id) {
        return partyRepository.save(party);
    }

    @Override
    public Iterable<Party> getPartiesOrganizedBy(Long userId) {
        return partyRepository.getPartiesByOrganizerId(userId);
    }
}

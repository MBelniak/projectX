package com.projectX.projectX.service;

import com.projectX.projectX.domain.Party;
import com.projectX.projectX.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartyService {

    private final PartyRepository partyRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public List<Party> getAll()
    {
        List<Party> parties = new ArrayList<>();
        partyRepository.findAll().forEach(parties::add);
        return parties;
    }

    public Party getParty(Long id)
    {
        return partyRepository.findById(id).orElse(null);
    }

    public Party saveParty(Party party) {
        return partyRepository.save(party);
    }

    public Party updateParty(Party party) {
        return partyRepository.save(party);
    }

    public Iterable<Party> getPartiesOrganizedBy(Long userId) {
        return partyRepository.getPartiesByOrganizerId(userId);
    }

}

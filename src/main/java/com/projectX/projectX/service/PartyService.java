package com.projectX.projectX.service;

import com.projectX.projectX.domain.Party;



public interface PartyService {
    Iterable<Party> getAll();
    Party getParty(Long id);
    Party addParty(Party party);
    Party updateParty(Party party, Long id);
}

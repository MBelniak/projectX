package com.projectX.projectX.service;

import com.projectX.projectX.domain.Party;


public interface PartyService {
    Iterable<Party> getAll();
    Party getParty(Long id);

    Party saveParty(Party party);
    Party updateParty(Party party, Long id);

    Iterable<Party> getPartiesOrganizedBy(Long userId);
}

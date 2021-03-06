package com.projectX.projectX.repository;

import com.projectX.projectX.domain.Party;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface PartyRepository extends PagingAndSortingRepository<Party, Long> {
    Iterable<Party> getPartiesByOrganizerId(Long userId);

}

package com.projectX.projectX.repository;

import com.projectX.projectX.domain.Party;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PartyRepository extends PagingAndSortingRepository<Party, Long> {
}

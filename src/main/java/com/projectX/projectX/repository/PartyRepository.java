package com.projectX.projectX.repository;

import com.projectX.projectX.domain.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PartyRepository extends PagingAndSortingRepository<Party, Long> {
}

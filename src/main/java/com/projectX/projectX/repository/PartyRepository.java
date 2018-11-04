package com.projectX.projectX.repository;

import com.projectX.projectX.domain.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {
}

package com.projectX.projectX.repository;

import com.projectX.projectX.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Role findRoleByName(String name);
}

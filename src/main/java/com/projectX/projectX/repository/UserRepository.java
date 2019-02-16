package com.projectX.projectX.repository;

import com.projectX.projectX.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findUserByEmail(String email);
}

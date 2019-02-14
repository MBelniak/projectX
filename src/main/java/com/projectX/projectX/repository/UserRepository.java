package com.projectX.projectX.repository;

import com.projectX.projectX.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}

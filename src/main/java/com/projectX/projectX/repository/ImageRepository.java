package com.projectX.projectX.repository;

import com.projectX.projectX.domain.Image;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {
    Image findByName(String name);
}

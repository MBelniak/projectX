package com.projectX.projectX.repository;

import com.projectX.projectX.domain.Image;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ImageRepository extends PagingAndSortingRepository<Image, Long>{
    public Image findByName(String name);
}

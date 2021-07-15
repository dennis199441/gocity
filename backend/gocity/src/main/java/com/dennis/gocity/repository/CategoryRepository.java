package com.dennis.gocity.repository;

import com.dennis.gocity.entity.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    
}

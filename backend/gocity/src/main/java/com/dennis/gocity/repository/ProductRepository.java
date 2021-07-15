package com.dennis.gocity.repository;

import com.dennis.gocity.entity.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}

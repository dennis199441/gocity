package com.dennis.gocity.controller;

import java.util.List;

import com.dennis.gocity.dto.CategoryDto;
import com.dennis.gocity.service.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        log.debug("invoked GET /categories");
        try {
            return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
        } catch(Exception e) {
            log.error("", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id) {
        log.debug("invoked GET /categories/" + id);
        try {
            return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
        } catch(Exception e) {
            log.error("", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto category) {
        log.debug("invoked POST /categories");
        try {
            return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.OK);
        } catch(Exception e) {
            log.error("", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto category) {
        log.debug("invoked PUT /categories/" + id);
        try {
            return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
        } catch(Exception e) {
            log.error("", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Long> deleteCategory(@PathVariable("id") Long id) {
        log.debug("invoked DELETE /categories/" + id);
        try {
            return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
        } catch(Exception e) {
            log.error("", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

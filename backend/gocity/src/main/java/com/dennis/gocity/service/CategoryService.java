package com.dennis.gocity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dennis.gocity.dto.CategoryDto;
import com.dennis.gocity.entity.Category;
import com.dennis.gocity.repository.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    public List<CategoryDto> getAllCategories() {
        log.debug("getAllCategories");
        List<CategoryDto> dtos = new ArrayList<>();
        List<Category> entities = new ArrayList<>();
        Iterable<Category> iterable = categoryRepository.findAll();
        iterable.forEach(entities::add);
        for (Category category : iterable) {
            dtos.add(mapEntityToDto(category));
        }

        return dtos;
    }

    public CategoryDto getCategoryById(Long id) throws NotFoundException {
        log.debug("getCategoryById(" + id + ")");
        Optional<Category> result = categoryRepository.findById(id);
        if (!result.isPresent()) {
            throw new NotFoundException("Category not found [id=" + id + "]");
        }
        Category category = result.get();
        return mapEntityToDto(category);
    }

    @Transactional
    public CategoryDto createCategory(CategoryDto category) {
        log.debug("createCategory: " + category.toString());
        Category entity = new Category();
        entity.setCategoryName(category.getCategoryName());
        categoryRepository.save(entity);
        category.setId(entity.getId());
        return category;
    }

    @Transactional
    public CategoryDto updateCategory(Long id, CategoryDto category) throws NotFoundException {
        log.debug("updateCategory: " + category.toString());
        Optional<Category> result = categoryRepository.findById(id);
        if (!result.isPresent()) {
            throw new NotFoundException("Category not found [id=" + id + "]");
        }

        Category entity = result.get();
        entity.setCategoryName(category.getCategoryName());
        categoryRepository.save(entity);
        return category;
    }

    @Transactional
    public Long deleteCategory(Long id) throws NotFoundException {
        log.debug("deleteCategory(" + id + ")");
        Optional<Category> result = categoryRepository.findById(id);
        if (!result.isPresent()) {
            throw new NotFoundException("Category not found [id=" + id + "]");
        }

        Category entity = result.get();
        categoryRepository.delete(entity);
        return id;
    }

    public CategoryDto mapEntityToDto(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setCategoryName(category.getCategoryName());
        return dto;
    }
}

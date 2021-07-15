package com.dennis.gocity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dennis.gocity.dto.ProductDto;
import com.dennis.gocity.entity.Category;
import com.dennis.gocity.entity.Product;
import com.dennis.gocity.exception.InvalidDataException;
import com.dennis.gocity.repository.CategoryRepository;
import com.dennis.gocity.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;

@Service
public class ProductService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public List<ProductDto> getAllProducts() {
        log.debug("getAllProducts");
        List<ProductDto> dtos = new ArrayList<>();
        List<Product> entities = new ArrayList<>();
        Iterable<Product> iterable = productRepository.findAll();
        iterable.forEach(entities::add);
        for(Product product : iterable) {
            dtos.add(mapEntityToDto(product));
        }
        
        return dtos;
    }

    public ProductDto getProductById(Long id) throws NotFoundException {
        log.debug("getProductById(" + id + ")");
        Optional<Product> result = productRepository.findById(id);
        if(!result.isPresent()) {
            throw new NotFoundException("Product not found [id=" + id + "]");
        }
        Product product = result.get();
        return mapEntityToDto(product);
    }

    @Transactional
    public ProductDto createProduct(ProductDto product) throws NotFoundException {
        log.debug("createProduct: " + product.toString());
        if (product.getCategory() == null) {
            throw new NotFoundException("Category can not be null");
        }
        
        Long categoryId = product.getCategory().getId();
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(!categoryOptional.isPresent()) {
            throw new NotFoundException("Category not found [id=" + categoryId + "]");
        }

        Category category = categoryOptional.get();
    
        Product entity = new Product();
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setCategory(category);
        entity.setCreationDate(new Date());
        entity.setUpdateDate(new Date());
        entity.setLastPurchasedDate(null);
        productRepository.save(entity);
        
        product.setId(entity.getId());
        product.setCreationDate(entity.getCreationDate());
        product.setUpdateDate(entity.getUpdateDate());
        
        return product;
    }

    @Transactional
    public ProductDto updateProduct(Long id, ProductDto product) throws NotFoundException, InvalidDataException {
        log.debug("updateProduct: " + product.toString());
        Optional<Product> result = productRepository.findById(id);
        if(!result.isPresent()) {
            throw new NotFoundException("Product not found [id=" + id + "]");
        }

        if (product.getCategory() == null) {
            throw new NotFoundException("Category can not be null");
        }
        
        Long categoryId = product.getCategory().getId();
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(!categoryOptional.isPresent()) {
            throw new NotFoundException("Category not found [id=" + categoryId + "]");
        }

        Category category = categoryOptional.get();

        Product entity = result.get();

        if(product.getLastPurchasedDate() != null && product.getLastPurchasedDate().compareTo(entity.getCreationDate()) < 0) {
            throw new InvalidDataException("last purchased date cannot be earlier than creation date.");
        }

        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setCategory(category);
        entity.setUpdateDate(new Date());
        if(product.getLastPurchasedDate() != null) {
            entity.setLastPurchasedDate(product.getLastPurchasedDate());
        }
        productRepository.save(entity);
        product.setCreationDate(entity.getCreationDate());
        product.setUpdateDate(entity.getUpdateDate());
        return product;
    }

    @Transactional
    public Long deleteProduct(Long id) throws NotFoundException {
        log.debug("deleteProduct(" + id + ")");
        Optional<Product> result = productRepository.findById(id);
        if(!result.isPresent()) {
            throw new NotFoundException("Product not found [id=" + id + "]");
        }
        Product entity = result.get();
        productRepository.delete(entity);
        return id;
    }

    public ProductDto mapEntityToDto(Product product) {
        if(product == null) {
            return null;
        }
        
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setCategory(categoryService.mapEntityToDto(product.getCategory()));
        dto.setCreationDate(product.getCreationDate());
        dto.setUpdateDate(product.getUpdateDate());
        dto.setLastPurchasedDate(product.getLastPurchasedDate());
        return dto;
    }
}

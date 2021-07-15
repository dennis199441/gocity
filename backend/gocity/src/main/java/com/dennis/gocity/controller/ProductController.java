package com.dennis.gocity.controller;

import java.util.List;

import com.dennis.gocity.dto.ProductDto;
import com.dennis.gocity.service.ProductService;
import com.dennis.gocity.service.PurchaseService;

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
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private PurchaseService purchaseService;
    
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.debug("invoked GET /products");
        try {
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        } catch(Exception e) {
            log.error("", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        log.debug("invoked GET /products/" + id);
        try {
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        } catch(Exception e) {
            log.error("", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/products/{id}/purchase")
    public ResponseEntity<ProductDto> purchaseProduct(@PathVariable("id") Long id, @RequestBody ProductDto product) {
        log.debug("invoked POST /products/" + id + "/purchase");
        try {
            return new ResponseEntity<>(purchaseService.purchaseProduct(id, product), HttpStatus.OK);
        } catch(Exception e) {
            log.error("", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        log.debug("invoked POST /products");
        try {
            return new ResponseEntity<>(productService.createProduct(product), HttpStatus.OK);
        } catch(Exception e) {
            log.error("", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto product) {
        log.debug("invoked PUT /products");
        try {
            return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
        } catch(Exception e) {
            log.error("", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable("id") Long id) {
        log.debug("invoked DELETE /products");
        try {
            return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
        } catch(Exception e) {
            log.error("", e);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

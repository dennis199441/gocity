package com.dennis.gocity.service;

import java.util.Date;

import com.dennis.gocity.dto.ProductDto;
import com.dennis.gocity.exception.InvalidDataException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class PurchaseService {

    @Autowired
    private ProductService productService;
    
    private static final Logger log = LoggerFactory.getLogger(PurchaseService.class);

    public ProductDto purchaseProduct(Long id, ProductDto product) throws NotFoundException, InvalidDataException {
        log.debug("purchaseProduct " + product.toString());
        product.setLastPurchasedDate(new Date());
        return productService.updateProduct(id, product);
    }
}

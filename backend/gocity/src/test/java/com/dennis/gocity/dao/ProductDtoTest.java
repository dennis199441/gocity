package com.dennis.gocity.dao;

import java.util.Date;

import com.dennis.gocity.dto.CategoryDto;
import com.dennis.gocity.dto.ProductDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductDtoTest {
    
    @Test
    public void testEquealsTrue() {
        Date date = new Date();
        CategoryDto categoryDto = new CategoryDto(Long.valueOf(1), "category 1");
        ProductDto dto1 = new ProductDto(Long.valueOf(1), "product 1", "description 1", categoryDto, date, date, date);
        ProductDto dto2 = new ProductDto(Long.valueOf(1), "product 1", "description 1", categoryDto, date, date, date);
        Assertions.assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquealsFalse() {
        Date date = new Date();
        CategoryDto categoryDto = new CategoryDto(Long.valueOf(1), "category 1");
        ProductDto dto1 = new ProductDto(Long.valueOf(1), "product 1", "description 1", categoryDto, date, date, date);
        ProductDto dto2 = new ProductDto(Long.valueOf(1), "product 2", "description 1", categoryDto, date, date, date);
        Assertions.assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquealsNull() {
        Date date = new Date();
        CategoryDto categoryDto = new CategoryDto(Long.valueOf(1), "category 1");
        ProductDto dto1 = new ProductDto(Long.valueOf(1), "product 1", "description 1", categoryDto, date, date, date);
        ProductDto dto2 = null;
        Assertions.assertFalse(dto1.equals(dto2));
    }
}

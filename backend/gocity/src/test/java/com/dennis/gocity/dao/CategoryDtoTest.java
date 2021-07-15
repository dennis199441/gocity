package com.dennis.gocity.dao;

import com.dennis.gocity.dto.CategoryDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CategoryDtoTest {
    
    @Test
    public void testEquealsTrue() {
        CategoryDto dto1 = new CategoryDto(Long.valueOf(1), "category 1");
        CategoryDto dto2 = new CategoryDto(Long.valueOf(1), "category 1");
        Assertions.assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testEquealsFalse() {
        CategoryDto dto1 = new CategoryDto(Long.valueOf(1), "category 1");
        CategoryDto dto2 = new CategoryDto(Long.valueOf(2), "category 1");
        Assertions.assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testEquealsNull() {
        CategoryDto dto1 = new CategoryDto(Long.valueOf(1), "category 1");
        CategoryDto dto2 = null;
        Assertions.assertFalse(dto1.equals(dto2));
    }
}

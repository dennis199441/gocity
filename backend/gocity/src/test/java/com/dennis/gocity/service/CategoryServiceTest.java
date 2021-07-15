package com.dennis.gocity.service;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.dennis.gocity.dto.CategoryDto;
import com.dennis.gocity.entity.Category;
import com.dennis.gocity.repository.CategoryRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javassist.NotFoundException;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {
    
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testGetAllCategoriesSize() {
        Category category1 = new Category(Long.valueOf(1), "category 1");
        Category category2 = new Category(Long.valueOf(2), "category 2");

        doReturn(Arrays.asList(category1, category2)).when(categoryRepository).findAll();

        List<CategoryDto> expected = new ArrayList<>();
        CategoryDto dto1 = new CategoryDto(Long.valueOf(1), "category 1");
        CategoryDto dto2 = new CategoryDto(Long.valueOf(2), "category 2");
        expected.add(dto1);
        expected.add(dto2);

        List<CategoryDto> result = categoryService.getAllCategories();

        Assertions.assertEquals(2, result.size(), "getAllCategories should return 2 categories");
    }

    @Test
    public void testGetAllCategoriesData() {
        Category category1 = new Category(Long.valueOf(1), "category 1");
        Category category2 = new Category(Long.valueOf(2), "category 2");

        doReturn(Arrays.asList(category1, category2)).when(categoryRepository).findAll();

        List<CategoryDto> expected = new ArrayList<>();
        CategoryDto dto1 = new CategoryDto(Long.valueOf(1), "category 1");
        CategoryDto dto2 = new CategoryDto(Long.valueOf(2), "category 2");
        expected.add(dto1);
        expected.add(dto2);

        List<CategoryDto> result = categoryService.getAllCategories();

        Assertions.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void testGetCategoryByIdData() throws NotFoundException {
        Long id = Long.valueOf(1);
        Optional<Category> optional = Optional.of(new Category(id, "category 1"));
        doReturn(optional).when(categoryRepository).findById(id);

        CategoryDto expected = new CategoryDto(id, "category 1");
        CategoryDto actual = categoryService.getCategoryById(id);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void testGetCategoryByIdNull() {
        Long id = Long.valueOf(1);
        doReturn(Optional.empty()).when(categoryRepository).findById(id);
        Assertions.assertThrows(NotFoundException.class, () -> categoryService.getCategoryById(id));
    }

    @Test
    public void testCreateCategorySuccess() throws NotFoundException {
        Long id = Long.valueOf(1);
        CategoryDto input = new CategoryDto(id, "category 1");
        CategoryDto actual = categoryService.createCategory(input);
        Assertions.assertEquals(input.getCategoryName(), actual.getCategoryName());
    }

    @Test
    public void testUpdateCategorySuccess() throws NotFoundException {
        Long id = Long.valueOf(1);
        
        Category category1 = new Category(id, "category 0");
        Optional<Category> optional = Optional.of(category1);
        doReturn(optional).when(categoryRepository).findById(id);

        CategoryDto input = new CategoryDto(id, "category 1");
        CategoryDto actual = categoryService.updateCategory(id, input);
        Assertions.assertEquals(input, actual);
    }

    @Test
    public void testUpdateCategoryFail() {
        Long id = Long.valueOf(1);
        
        doReturn(Optional.empty()).when(categoryRepository).findById(id);
        
        CategoryDto input = new CategoryDto(id, "category 1");
        Assertions.assertThrows(NotFoundException.class, () -> categoryService.updateCategory(id, input));
    }


    @Test
    public void testDeleteCategorySuccess() throws NotFoundException {
        Long id = Long.valueOf(1);
        Category category1 = new Category(id, "category 1");
        Optional<Category> optional = Optional.of(category1);
        
        doReturn(optional).when(categoryRepository).findById(id);
        
        Long actual = categoryService.deleteCategory(id);
        Assertions.assertEquals(id, actual);
    }

    @Test
    public void testDeleteCategoryFail() {
        Long id = Long.valueOf(1);
        doReturn(Optional.empty()).when(categoryRepository).findById(id);
        Assertions.assertThrows(NotFoundException.class, () -> categoryService.deleteCategory(id));
    }

    @Test
    public void testMapEntityToDtoSccess() {
        Long id = Long.valueOf(1);
        Category input = new Category(id, "category 1");
        CategoryDto expected = new CategoryDto(id, "category 1");
        CategoryDto actual = categoryService.mapEntityToDto(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMapEntityToDtoNull() {
        CategoryDto actual = categoryService.mapEntityToDto(null);
        Assertions.assertEquals(null, actual);
    }
}

package com.dennis.gocity.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import com.dennis.gocity.dto.CategoryDto;
import com.dennis.gocity.service.CategoryService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javassist.NotFoundException;

@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    public void testGetAllCategoriesResponseBodySize() {
        CategoryDto dto1 = new CategoryDto(Long.valueOf(1), "category 1");
        CategoryDto dto2 = new CategoryDto(Long.valueOf(2), "category 2");
        List<CategoryDto> expectedBody = new ArrayList<>();
        expectedBody.add(dto1);
        expectedBody.add(dto2);

        doReturn(expectedBody).when(categoryService).getAllCategories();

        ResponseEntity<List<CategoryDto>> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<List<CategoryDto>> actual = categoryController.getAllCategories();
        Assertions.assertEquals(expected.getBody().size(), actual.getBody().size());
    }

    @Test
    public void testGetAllCategoriesResponseBodyData() {
        CategoryDto dto1 = new CategoryDto(Long.valueOf(1), "category 1");
        CategoryDto dto2 = new CategoryDto(Long.valueOf(2), "category 2");
        List<CategoryDto> expectedBody = new ArrayList<>();
        expectedBody.add(dto1);
        expectedBody.add(dto2);

        doReturn(expectedBody).when(categoryService).getAllCategories();

        ResponseEntity<List<CategoryDto>> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<List<CategoryDto>> actual = categoryController.getAllCategories();
        Assertions.assertArrayEquals(expected.getBody().toArray(), actual.getBody().toArray());
    }

    @Test
    public void testGetAllCategoriesResponseEntity() {
        CategoryDto dto1 = new CategoryDto(Long.valueOf(1), "category 1");
        CategoryDto dto2 = new CategoryDto(Long.valueOf(2), "category 2");
        List<CategoryDto> expectedBody = new ArrayList<>();
        expectedBody.add(dto1);
        expectedBody.add(dto2);

        doReturn(expectedBody).when(categoryService).getAllCategories();

        ResponseEntity<List<CategoryDto>> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<List<CategoryDto>> actual = categoryController.getAllCategories();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllCategoriesEmpty() {
        doReturn(new ArrayList<CategoryDto>()).when(categoryService).getAllCategories();
        ResponseEntity<List<CategoryDto>> expected = new ResponseEntity<>(new ArrayList<CategoryDto>(), HttpStatus.OK);
        ResponseEntity<List<CategoryDto>> actual = categoryController.getAllCategories();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetCategoryByIdResponseEntity() throws NotFoundException {
        Long id = Long.valueOf(1);
        CategoryDto expectedBody = new CategoryDto(id, "category 1");
        doReturn(expectedBody).when(categoryService).getCategoryById(id);

        ResponseEntity<CategoryDto> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<CategoryDto> actual = categoryController.getCategoryById(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetCategoryByIdInvalidId() throws NotFoundException {
        Long id = Long.valueOf(1);
        doThrow(NotFoundException.class).when(categoryService).getCategoryById(id);
        ResponseEntity<CategoryDto> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<CategoryDto> actual = categoryController.getCategoryById(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCreateCategorySuccess() throws NotFoundException {
        Long id = Long.valueOf(1);
        CategoryDto input = new CategoryDto(id, "category 1");
        CategoryDto expectedBody = new CategoryDto(id, "category 1");
        doReturn(expectedBody).when(categoryService).createCategory(input);

        ResponseEntity<CategoryDto> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<CategoryDto> actual = categoryController.createCategory(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateCategorySuccess() throws NotFoundException {
        Long id = Long.valueOf(1);
        CategoryDto expectedBody = new CategoryDto(id, "category 1");
        CategoryDto input = new CategoryDto(id, "category 1");
        doReturn(expectedBody).when(categoryService).updateCategory(id, input);

        ResponseEntity<CategoryDto> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<CategoryDto> actual = categoryController.updateCategory(id, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateCategoryFail() throws NotFoundException {
        Long id = Long.valueOf(1);
        CategoryDto input = new CategoryDto(id, "category 1");
        doThrow(NotFoundException.class).when(categoryService).updateCategory(id, input);

        ResponseEntity<CategoryDto> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<CategoryDto> actual = categoryController.updateCategory(id, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteCategorySuccess() throws NotFoundException {
        Long id = Long.valueOf(1);
        doReturn(id).when(categoryService).deleteCategory(id);

        ResponseEntity<Long> expected = new ResponseEntity<>(id, HttpStatus.OK);
        ResponseEntity<Long> actual = categoryController.deleteCategory(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteCategoryFail() throws NotFoundException {
        Long id = Long.valueOf(1);
        doThrow(NotFoundException.class).when(categoryService).deleteCategory(id);

        ResponseEntity<Long> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<Long> actual = categoryController.deleteCategory(id);
        Assertions.assertEquals(expected, actual);
    }
}

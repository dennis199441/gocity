package com.dennis.gocity.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dennis.gocity.dto.CategoryDto;
import com.dennis.gocity.dto.ProductDto;
import com.dennis.gocity.exception.InvalidDataException;
import com.dennis.gocity.service.ProductService;
import com.dennis.gocity.service.PurchaseService;

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
public class ProductControllerTest {

    @Mock
    private ProductService productService;
    @Mock
    private PurchaseService purchaseService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testGetAllProductsResponseBodySize() {
        Date date = new Date();
        CategoryDto categoryDto1 = new CategoryDto(Long.valueOf(1), "category 1");
        List<ProductDto> expectedBody = new ArrayList<>();
        ProductDto dto1 = new ProductDto(Long.valueOf(1), "product 1", "description 1", categoryDto1, date, date, date);
        ProductDto dto2 = new ProductDto(Long.valueOf(2), "product 2", "description 2", categoryDto1, date, date, date);
        expectedBody.add(dto1);
        expectedBody.add(dto2);

        doReturn(expectedBody).when(productService).getAllProducts();

        ResponseEntity<List<ProductDto>> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<List<ProductDto>> actual = productController.getAllProducts();
        Assertions.assertEquals(expected.getBody().size(), actual.getBody().size());
    }

    @Test
    public void testGetAllProductsResponseBodyData() {
        Date date = new Date();
        CategoryDto categoryDto1 = new CategoryDto(Long.valueOf(1), "category 1");
        List<ProductDto> expectedBody = new ArrayList<>();
        ProductDto dto1 = new ProductDto(Long.valueOf(1), "product 1", "description 1", categoryDto1, date, date, date);
        ProductDto dto2 = new ProductDto(Long.valueOf(2), "product 2", "description 2", categoryDto1, date, date, date);
        expectedBody.add(dto1);
        expectedBody.add(dto2);

        doReturn(expectedBody).when(productService).getAllProducts();

        ResponseEntity<List<ProductDto>> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<List<ProductDto>> actual = productController.getAllProducts();
        Assertions.assertArrayEquals(expected.getBody().toArray(), actual.getBody().toArray());
    }

    @Test
    public void testGetAllProductsResponseEntity() {
        Date date = new Date();
        CategoryDto categoryDto1 = new CategoryDto(Long.valueOf(1), "category 1");
        List<ProductDto> expectedBody = new ArrayList<>();
        ProductDto dto1 = new ProductDto(Long.valueOf(1), "product 1", "description 1", categoryDto1, date, date, date);
        ProductDto dto2 = new ProductDto(Long.valueOf(2), "product 2", "description 2", categoryDto1, date, date, date);
        expectedBody.add(dto1);
        expectedBody.add(dto2);

        doReturn(expectedBody).when(productService).getAllProducts();

        ResponseEntity<List<ProductDto>> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<List<ProductDto>> actual = productController.getAllProducts();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllProductsEmpty() {
        doReturn(new ArrayList<ProductDto>()).when(productService).getAllProducts();
        ResponseEntity<List<ProductDto>> expected = new ResponseEntity<>(new ArrayList<ProductDto>(), HttpStatus.OK);
        ResponseEntity<List<ProductDto>> actual = productController.getAllProducts();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetProductByIdResponseEntity() throws NotFoundException {
        Long id = Long.valueOf(1);
        Date date = new Date();
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto expectedBody = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, date);

        doReturn(expectedBody).when(productService).getProductById(id);

        ResponseEntity<ProductDto> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<ProductDto> actual = productController.getProductById(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetProductByIdInvalidId() throws NotFoundException {
        Long id = Long.valueOf(1);
        doThrow(NotFoundException.class).when(productService).getProductById(id);
        ResponseEntity<ProductDto> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<ProductDto> actual = productController.getProductById(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPurchaseProductSuccess() throws NotFoundException, InvalidDataException {
        Long id = Long.valueOf(1);
        Date date = new Date();
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, null);
        ProductDto expectedBody = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, date);
        doReturn(expectedBody).when(purchaseService).purchaseProduct(id, input);

        ResponseEntity<ProductDto> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<ProductDto> actual = productController.purchaseProduct(id, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPurchaseProductFail() throws NotFoundException, InvalidDataException {
        Long id = Long.valueOf(1);
        Date date = new Date();
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, null);

        doThrow(NotFoundException.class).when(purchaseService).purchaseProduct(id, input);

        ResponseEntity<ProductDto> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<ProductDto> actual = productController.purchaseProduct(id, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCreateProductSuccess() throws NotFoundException {
        Long id = Long.valueOf(1);
        Date date = new Date();
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto expectedBody = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, null);
        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, null, null, null);

        doReturn(expectedBody).when(productService).createProduct(input);

        ResponseEntity<ProductDto> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<ProductDto> actual = productController.createProduct(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCreateProductFail() throws NotFoundException {
        Long id = Long.valueOf(1);
        ProductDto input = new ProductDto(id, "product 1", "description 1", null, null, null, null);
        doThrow(NotFoundException.class).when(productService).createProduct(input);

        ResponseEntity<ProductDto> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<ProductDto> actual = productController.createProduct(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateProductSuccess() throws NotFoundException, InvalidDataException {
        Long id = Long.valueOf(1);
        Date date = new Date();
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto expectedBody = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, date);
        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, date);

        doReturn(expectedBody).when(productService).updateProduct(id, input);

        ResponseEntity<ProductDto> expected = new ResponseEntity<>(expectedBody, HttpStatus.OK);
        ResponseEntity<ProductDto> actual = productController.updateProduct(id, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateProductFail() throws NotFoundException, InvalidDataException {
        Long id = Long.valueOf(1);
        Date date = new Date();
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, date);
        doThrow(NotFoundException.class).when(productService).updateProduct(id, input);

        ResponseEntity<ProductDto> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<ProductDto> actual = productController.updateProduct(id, input);
        Assertions.assertEquals(expected, actual);
    }
    // new Date(System.currentTimeMillis()-24*60*60*1000);

    @Test
    public void testUpdateProductInvalidDateCondition() throws NotFoundException, InvalidDataException {
        Long id = Long.valueOf(1);
        Date date1 = new Date();
        Date date2 = new Date(System.currentTimeMillis()-24*60*60*1000);
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, date1, date1, date2);
        doThrow(InvalidDataException.class).when(productService).updateProduct(id, input);

        ResponseEntity<ProductDto> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<ProductDto> actual = productController.updateProduct(id, input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteProductSuccess() throws NotFoundException {
        Long id = Long.valueOf(1);
        doReturn(id).when(productService).deleteProduct(id);

        ResponseEntity<Long> expected = new ResponseEntity<>(id, HttpStatus.OK);
        ResponseEntity<Long> actual = productController.deleteProduct(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteProductFail() throws NotFoundException {
        Long id = Long.valueOf(1);
        doThrow(NotFoundException.class).when(productService).deleteProduct(id);

        ResponseEntity<Long> expected = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<Long> actual = productController.deleteProduct(id);
        Assertions.assertEquals(expected, actual);
    }
}

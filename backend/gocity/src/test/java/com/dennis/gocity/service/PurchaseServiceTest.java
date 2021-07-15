package com.dennis.gocity.service;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.Date;

import com.dennis.gocity.dto.CategoryDto;
import com.dennis.gocity.dto.ProductDto;
import com.dennis.gocity.exception.InvalidDataException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javassist.NotFoundException;

@ExtendWith(SpringExtension.class)
public class PurchaseServiceTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private PurchaseService purchaseService;

    @Test
    public void testPurchaseProductSuccess() throws NotFoundException, InvalidDataException {
        Long id = Long.valueOf(1);
        Date date = new Date();
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto dto = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, null);
        ProductDto expected = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, date);
        doReturn(expected).when(productService).updateProduct(id, dto);

        ProductDto actual = purchaseService.purchaseProduct(id, dto);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testPurchaseProductFail() throws NotFoundException, InvalidDataException {
        Long id = Long.valueOf(1);
        Date date = new Date();
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto dto = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, null);
        doThrow(NotFoundException.class).when(productService).updateProduct(id, dto);
        Assertions.assertThrows(NotFoundException.class, () -> purchaseService.purchaseProduct(id, dto));
    }
}

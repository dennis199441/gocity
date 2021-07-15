package com.dennis.gocity.service;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dennis.gocity.dto.CategoryDto;
import com.dennis.gocity.dto.ProductDto;
import com.dennis.gocity.entity.Category;
import com.dennis.gocity.entity.Product;
import com.dennis.gocity.exception.InvalidDataException;
import com.dennis.gocity.repository.CategoryRepository;
import com.dennis.gocity.repository.ProductRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javassist.NotFoundException;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProductsSize() {
        Date date = new Date();
        Category category1 = new Category(Long.valueOf(1), "category 1");
        CategoryDto categoryDto1 = new CategoryDto(Long.valueOf(1), "category 1");
        Product product1 = new Product(Long.valueOf(1), "product 1", "description 1",
                new Category(Long.valueOf(1), "category 1"), date, date, date);
        Product product2 = new Product(Long.valueOf(2), "product 2", "description 2",
                new Category(Long.valueOf(2), "category 2"), date, date, date);

        doReturn(Arrays.asList(product1, product2)).when(productRepository).findAll();
        doReturn(categoryDto1).when(categoryService).mapEntityToDto(category1);

        List<ProductDto> expected = new ArrayList<>();
        ProductDto dto1 = new ProductDto(Long.valueOf(1), "product 1", "description 1", categoryDto1, date, date, date);
        ProductDto dto2 = new ProductDto(Long.valueOf(2), "product 2", "description 2", categoryDto1, date, date, date);
        expected.add(dto1);
        expected.add(dto2);

        List<ProductDto> result = productService.getAllProducts();

        Assertions.assertEquals(2, result.size(), "getAllProducts should return 2 products");
    }

    @Test
    public void testGetAllProductsData() {
        Date date = new Date();
        Category category1 = new Category(Long.valueOf(1), "category 1");
        CategoryDto categoryDto1 = new CategoryDto(Long.valueOf(1), "category 1");
        Product product1 = new Product(Long.valueOf(1), "product 1", "description 1", category1, date, date, date);
        Product product2 = new Product(Long.valueOf(2), "product 2", "description 2", category1, date, date, date);

        doReturn(Arrays.asList(product1, product2)).when(productRepository).findAll();
        doReturn(categoryDto1).when(categoryService).mapEntityToDto(category1);

        List<ProductDto> expected = new ArrayList<>();
        ProductDto dto1 = new ProductDto(Long.valueOf(1), "product 1", "description 1", categoryDto1, date, date, date);
        ProductDto dto2 = new ProductDto(Long.valueOf(2), "product 2", "description 2", categoryDto1, date, date, date);
        expected.add(dto1);
        expected.add(dto2);

        List<ProductDto> result = productService.getAllProducts();

        Assertions.assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void testGetProductByIdData() throws NotFoundException  {
        Date date = new Date();
        Long id = Long.valueOf(1);
        Category category1 = new Category(id, "category 1");
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        Product product1 = new Product(id, "product 1", "description 1", category1, date, date, date);
        Optional<Product> optional = Optional.of(product1);
        doReturn(optional).when(productRepository).findById(id);
        doReturn(categoryDto1).when(categoryService).mapEntityToDto(category1);

        ProductDto expected = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, date);

        ProductDto actual = productService.getProductById(id);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetProductByIdNull() {
        Long id = Long.valueOf(1);
        doReturn(Optional.empty()).when(productRepository).findById(id);
        Assertions.assertThrows(NotFoundException.class, () -> productService.getProductById(id));
    }

    @Test
    public void testCreateProductData() throws NotFoundException {
        Long id = Long.valueOf(1);
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        Optional<Category> categoryOptional = Optional.of(new Category(id, "category 1"));
        doReturn(categoryOptional).when(categoryRepository).findById(id);
        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, null, null, null);
        ProductDto actual = productService.createProduct(input);
        Assertions.assertEquals(input.getName(), actual.getName());
        Assertions.assertEquals(input.getDescription(), actual.getDescription());
        Assertions.assertEquals(input.getCategory(), actual.getCategory());
        Assertions.assertNotNull(actual.getCreationDate());
        Assertions.assertNotNull(actual.getUpdateDate());
        Assertions.assertNull(actual.getLastPurchasedDate());
    }

    @Test
    public void testCreateProductInvalidCategoryId() {
        Long id = Long.valueOf(1);
        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        doReturn(Optional.empty()).when(categoryRepository).findById(id);
        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, null, null, null);
        Assertions.assertThrows(NotFoundException.class, () -> productService.createProduct(input));
    }

    @Test
    public void testUpdateProductData() throws NotFoundException, InvalidDataException {
        Long id = Long.valueOf(1);
        Date date = new Date();

        Category category1 = new Category(id, "category 1");
        Product product1 = new Product(id, "product 1", "description 1", category1, date, date, null);
        Optional<Product> optional = Optional.of(product1);
        doReturn(optional).when(productRepository).findById(id);

        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        Optional<Category> categoryOptional = Optional.of(new Category(id, "category 1"));
        doReturn(categoryOptional).when(categoryRepository).findById(id);

        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, date);
        ProductDto actual = productService.updateProduct(id, input);
        Assertions.assertEquals(input, actual);
    }

    @Test
    public void testUpdateProductInvalidProductId() {
        Long id = Long.valueOf(1);
        Date date = new Date();
        doReturn(Optional.empty()).when(productRepository).findById(id);

        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, date);
        Assertions.assertThrows(NotFoundException.class, () -> productService.updateProduct(id, input));
    }

    @Test
    public void testUpdateProductNullCategory() {
        Long id = Long.valueOf(1);
        Date date = new Date();

        Category category1 = new Category(id, "category 1");
        Product product1 = new Product(id, "product 1", "description 1", category1, date, date, null);
        Optional<Product> optional = Optional.of(product1);
        doReturn(optional).when(productRepository).findById(id);
        doReturn(Optional.empty()).when(categoryRepository).findById(id);

        ProductDto input = new ProductDto(id, "product 1", "description 1", null, date, date, date);
        Assertions.assertThrows(NotFoundException.class, () -> productService.updateProduct(id, input));
    }

    @Test
    public void testUpdateProductInvalidCategoryId() {
        Long id = Long.valueOf(1);
        Date date = new Date();

        Category category1 = new Category(id, "category 1");
        Product product1 = new Product(id, "product 1", "description 1", category1, date, date, null);
        Optional<Product> optional = Optional.of(product1);
        doReturn(optional).when(productRepository).findById(id);

        doReturn(Optional.empty()).when(categoryRepository).findById(id);

        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, date);
        Assertions.assertThrows(NotFoundException.class, () -> productService.updateProduct(id, input));
    }

    @Test
    public void testUpdateProductInvalidDateCondition() {
        Long id = Long.valueOf(1);
        Date date1 = new Date();
        Date date2 = new Date(System.currentTimeMillis()-24*60*60*1000);
        Category category1 = new Category(id, "category 1");
        Product product1 = new Product(id, "product 1", "description 1", category1, date1, date1, null);
        Optional<Product> optional = Optional.of(product1);
        doReturn(optional).when(productRepository).findById(id); 

        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        Optional<Category> categoryOptional = Optional.of(new Category(id, "category 1"));
        doReturn(categoryOptional).when(categoryRepository).findById(id);

        ProductDto input = new ProductDto(id, "product 1", "description 1", categoryDto1, date1, date1, date2);
        Assertions.assertThrows(InvalidDataException.class, () -> productService.updateProduct(id, input));
    }

    @Test
    public void testDeleteProductSuccess() throws NotFoundException {
        Long id = Long.valueOf(1);
        Date date = new Date();
        Category category1 = new Category(id, "category 1");
        Product product1 = new Product(id, "product 1", "description 1", category1, date, date, null);
        Optional<Product> optional = Optional.of(product1);
        doReturn(optional).when(productRepository).findById(id);

        Long actual = productService.deleteProduct(id);
        Assertions.assertEquals(1, actual);
    }

    @Test
    public void testDeleteProductInvalidProductId() {
        Long id = Long.valueOf(1);
        doReturn(Optional.empty()).when(productRepository).findById(id);
        Assertions.assertThrows(NotFoundException.class, () -> productService.deleteProduct(id));
    }

    @Test
    public void testMapEntityToDtoSuccess() {
        Long id = Long.valueOf(1);
        Date date = new Date();

        Category category1 = new Category(id, "category 1");
        Product input = new Product(id, "product 1", "description 1", category1, date, date, null);

        CategoryDto categoryDto1 = new CategoryDto(id, "category 1");
        ProductDto expected = new ProductDto(id, "product 1", "description 1", categoryDto1, date, date, null);

        doReturn(categoryDto1).when(categoryService).mapEntityToDto(input.getCategory());

        ProductDto actual = productService.mapEntityToDto(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMapEntityToDtoNullProduct() {
        ProductDto actual = productService.mapEntityToDto(null);
        Assertions.assertNull(actual);
    }

    @Test
    public void testMapEntityToDtoNullCategory() {
        Long id = Long.valueOf(1);
        Date date = new Date();

        Product input = new Product(id, "product 1", "description 1", null, date, date, null);
        ProductDto expected = new ProductDto(id, "product 1", "description 1", null, date, date, null);
        doReturn(null).when(categoryService).mapEntityToDto(input.getCategory());

        ProductDto actual = productService.mapEntityToDto(input);
        Assertions.assertEquals(expected, actual);
    }
}

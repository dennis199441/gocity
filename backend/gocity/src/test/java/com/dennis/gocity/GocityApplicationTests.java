package com.dennis.gocity;

import com.dennis.gocity.controller.CategoryController;
import com.dennis.gocity.controller.ProductController;
import com.dennis.gocity.repository.CategoryRepository;
import com.dennis.gocity.repository.ProductRepository;
import com.dennis.gocity.service.CategoryService;
import com.dennis.gocity.service.ProductService;
import com.dennis.gocity.service.PurchaseService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GocityApplicationTests {

	@Autowired
    private CategoryController categoryController;
	@Autowired
	private ProductController productController;
	@Autowired
    private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;


	@Test
	void contextLoads() {
		Assertions.assertNotNull(categoryController);
		Assertions.assertNotNull(productController);
		Assertions.assertNotNull(categoryService);
		Assertions.assertNotNull(productService);
		Assertions.assertNotNull(purchaseService);
		Assertions.assertNotNull(categoryRepository);
		Assertions.assertNotNull(productRepository);
	}

}

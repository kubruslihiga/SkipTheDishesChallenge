package br.challenge.skipthedishes;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import br.challenge.skipthedishes.exception.SkiptheDishesException;
import br.challenge.skipthedishes.model.Product;
import br.challenge.skipthedishes.model.ProductQuery;
import br.challenge.skipthedishes.repository.ProductRepository;
import br.challenge.skipthedishes.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class ProductServiceTest {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductService productService;

	@Test
	@Sql({"/test-reset-order.sql", "/test-product.sql"})
	public void testProductFilter() throws SkiptheDishesException {
		ProductQuery query = new ProductQuery();
		query.setName("DML Product 1");
		Assert.assertEquals(productService.findProducts(query).size(), 1);
	}

	@Test
	public void testProductFilter2() throws SkiptheDishesException {
		ProductQuery query = new ProductQuery();
		query.setName("DML Product 2");
		query.setCode("DML_1");
		Assert.assertEquals(productService.findProducts(query).size(), 0);
	}
	
	@Test
	@Sql({"/test-product-option.sql"})
	public void testProductFilter3() throws SkiptheDishesException {
		ProductQuery query = new ProductQuery();
		query.setColor("blue");
		query.setWidth(BigDecimal.valueOf(1.0));
		List<Product> products = productService.findProducts(query);
		Assert.assertEquals(products.size(), 1);
		Product product = products.iterator().next();
		Assert.assertEquals("DML Product 1", product.getName());
		
	}
}

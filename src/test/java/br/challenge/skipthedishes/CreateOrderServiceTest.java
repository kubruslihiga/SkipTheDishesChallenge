package br.challenge.skipthedishes;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;

import br.challenge.skipthedishes.exception.SkiptheDishesException;
import br.challenge.skipthedishes.model.Order;
import br.challenge.skipthedishes.model.Product;
import br.challenge.skipthedishes.repository.ProductRepository;
import br.challenge.skipthedishes.repository.UserRepository;
import br.challenge.skipthedishes.service.OrderService;
import br.challenge.skipthedishes.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class CreateOrderServiceTest {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Test
	@Sql(scripts = { "/test-reset-order.sql", "/test-product.sql", "/test-user.sql" }, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED), executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testCreateOrder() throws SkiptheDishesException {
		Product product1 = productRepository.findById(Long.valueOf(1)).get();
		Product product2 = productRepository.findById(Long.valueOf(2)).get();
		Product product3 = productRepository.findById(Long.valueOf(3)).get();
		Order order = new Order();
		order.setProducts(Arrays.asList(product1, product2, product3));
		Order createdOrder = orderService.createOrder(order, userRepository.findById(Long.valueOf(1)).get());
		Assert.assertNotNull(createdOrder);
	}

	
}

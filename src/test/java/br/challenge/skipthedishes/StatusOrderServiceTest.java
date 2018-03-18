package br.challenge.skipthedishes;

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
import br.challenge.skipthedishes.model.OrderStatus;
import br.challenge.skipthedishes.repository.ProductRepository;
import br.challenge.skipthedishes.repository.UserRepository;
import br.challenge.skipthedishes.service.OrderService;
import br.challenge.skipthedishes.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class StatusOrderServiceTest {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Test
	@Sql(scripts = { "/test-reset-order.sql", "/test-product.sql", "/test-user.sql", "/test-order.sql" }, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED), executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testCreateOrder() throws SkiptheDishesException {
		Order order = orderService.findOrderByCode("COD_ORDER_1");
		Assert.assertEquals(OrderStatus.CREATED, order.getStatus());
	}

	
}

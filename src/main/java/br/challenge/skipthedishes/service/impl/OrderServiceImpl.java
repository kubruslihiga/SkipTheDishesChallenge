package br.challenge.skipthedishes.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.challenge.skipthedishes.exception.SkiptheDishesException;
import br.challenge.skipthedishes.model.Order;
import br.challenge.skipthedishes.model.OrderStatus;
import br.challenge.skipthedishes.model.Product;
import br.challenge.skipthedishes.model.User;
import br.challenge.skipthedishes.repository.OrderRepository;
import br.challenge.skipthedishes.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order createOrder(@Valid Order order, User user) throws SkiptheDishesException {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (Product product : order.getProducts()) {
			totalPrice = totalPrice.add(product.getPrice());
		}
		order.setTotalPrice(totalPrice);
		order.setCreatedTime(new Date());
		order.setStatus(OrderStatus.CREATED);
		order.setCode(UUID.randomUUID().toString());
		order.setUser(user);
		Order save = orderRepository.save(order);
		return save;
	}

	@Override
	public Order cancelOrder(String orderCode, User user) throws SkiptheDishesException {
		Order order = findOrderByCode(orderCode);
		if (order.getStatus() == OrderStatus.CANCELED) {
			throw new SkiptheDishesException("The order with code " + orderCode + " is already canceled."); 
		}
		order.setStatus(OrderStatus.CANCELED);
		order.setCanceledTime(new Date());
		order.setCancelUser(user);
		Order save = orderRepository.save(order);
		return save;
	}

	@Override
	public Order findOrderByCode(String code) throws SkiptheDishesException {
		Order order = orderRepository.findByCode(code);
		if (order == null) {
			throw new SkiptheDishesException("The order does not exist by code " + code + ".");
		}
		return order;
	}
}

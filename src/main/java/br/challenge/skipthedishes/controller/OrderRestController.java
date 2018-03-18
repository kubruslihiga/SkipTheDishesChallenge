package br.challenge.skipthedishes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.challenge.skipthedishes.exception.SkiptheDishesException;
import br.challenge.skipthedishes.model.Order;
import br.challenge.skipthedishes.model.User;
import br.challenge.skipthedishes.service.OrderService;

@Controller
@RequestMapping(value="/order")
public class OrderRestController {

	@Autowired
	private OrderService orderService;

	@PreAuthorize("hasRole('AUTHENTICATED')")
	@RequestMapping(value = "/{code}/status", method = RequestMethod.GET)
	public String findOrderByCode(@PathVariable String code) throws SkiptheDishesException {
		Order order = orderService.findOrderByCode(code);
		return order.getStatus().name();
	}

	@PreAuthorize("hasRole('AUTHENTICATED')")
	@RequestMapping(method = RequestMethod.PUT)
	public Order createOrder(@RequestBody Order order, Authentication auth) throws SkiptheDishesException {
		User user = (User) auth.getPrincipal();
		return orderService.createOrder(order, user);
	}

	@PreAuthorize("hasRole('AUTHENTICATED')")
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	public Order cancelOrder(@PathVariable String code, Authentication auth) throws SkiptheDishesException {
		User user = (User) auth.getPrincipal();
		return orderService.cancelOrder(code, user);
	}
}

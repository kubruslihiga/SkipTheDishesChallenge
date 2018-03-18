package br.challenge.skipthedishes.service;

import javax.validation.Valid;

import br.challenge.skipthedishes.exception.SkiptheDishesException;
import br.challenge.skipthedishes.model.Order;
import br.challenge.skipthedishes.model.User;

public interface OrderService {

	Order cancelOrder(String orderCode, User user) throws SkiptheDishesException;
	
	Order findOrderByCode(String code) throws SkiptheDishesException;

	Order createOrder(@Valid Order order, User user) throws SkiptheDishesException;
}

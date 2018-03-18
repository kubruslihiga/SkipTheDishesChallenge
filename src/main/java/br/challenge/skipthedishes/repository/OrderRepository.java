package br.challenge.skipthedishes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.challenge.skipthedishes.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Order findByCode(String code);

}

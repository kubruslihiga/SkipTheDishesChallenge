package br.challenge.skipthedishes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.challenge.skipthedishes.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

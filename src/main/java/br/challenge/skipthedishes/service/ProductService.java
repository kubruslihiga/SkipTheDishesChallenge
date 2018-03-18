package br.challenge.skipthedishes.service;

import java.util.List;

import br.challenge.skipthedishes.exception.SkiptheDishesException;
import br.challenge.skipthedishes.model.Product;
import br.challenge.skipthedishes.model.ProductQuery;

public interface ProductService {

	List<Product> findProducts(ProductQuery productQuery) throws SkiptheDishesException;
}

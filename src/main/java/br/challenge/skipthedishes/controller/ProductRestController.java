package br.challenge.skipthedishes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.challenge.skipthedishes.exception.SkiptheDishesException;
import br.challenge.skipthedishes.model.Product;
import br.challenge.skipthedishes.model.ProductQuery;
import br.challenge.skipthedishes.service.ProductService;

@Controller
@RequestMapping(value="/product")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@PreAuthorize("hasRole('AUTHENTICATED')")
	@RequestMapping(method = RequestMethod.GET)
	public List<Product> findProduct(ProductQuery productQuery) throws SkiptheDishesException {
		return productService.findProducts(productQuery);
	}
}

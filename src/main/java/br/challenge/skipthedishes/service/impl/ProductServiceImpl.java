package br.challenge.skipthedishes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.challenge.skipthedishes.exception.SkiptheDishesException;
import br.challenge.skipthedishes.model.Product;
import br.challenge.skipthedishes.model.ProductQuery;
import br.challenge.skipthedishes.service.ProductService;

@Repository
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Product> findProducts(ProductQuery productQuery) throws SkiptheDishesException {
		StringBuilder sb = new StringBuilder("select p from Product p");
		if (productQuery.getWidth() != null || productQuery.getHeight() != null || StringUtils.isNotBlank(productQuery.getColor())) {
			sb.append(" JOIN p.options options");
		}
		sb.append(" WHERE 1 = 1");
		Map<String, Object> parameters = new HashMap<>();
		if (StringUtils.isNotBlank(productQuery.getCode())) {
			sb.append(" and p.code = :code");
			parameters.put("code", productQuery.getCode());
		}
		if (StringUtils.isNotBlank(productQuery.getName())) {
			sb.append(" and p.name = :name");
			parameters.put("name", productQuery.getName());
		}
		if (productQuery.getPrice() != null) {
			sb.append(" and p.price = :price");
			parameters.put("price", productQuery.getPrice());
		}
		if (StringUtils.isNotBlank(productQuery.getColor())) {
			sb.append(" and options.color = :color");
			parameters.put("color", productQuery.getColor());
		}
		if (productQuery.getWidth() != null) {
			sb.append(" and options.width = :width");
			parameters.put("width", productQuery.getWidth());
		}
		if (productQuery.getHeight() != null) {
			sb.append(" and options.height = :height");
			parameters.put("height", productQuery.getHeight());
		}
		TypedQuery<Product> query = entityManager.createQuery(sb.toString(), Product.class);
		parameters.entrySet().stream().forEach(entry -> {
			query.setParameter(entry.getKey(), entry.getValue());
		});
		return query.getResultList();
	}

}

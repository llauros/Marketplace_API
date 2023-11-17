package com.marketplace.core.ports.inbound;

import java.util.List;

import com.marketplace.core.domain.model.Product;

public interface ProductService {
	
	public Product create(Product model);
	public Product update(Product model);
	public Product findById(Long id);
	public List<Product> findAll();
	public boolean deleteById(Long id);
	
}

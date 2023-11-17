package com.marketplace.core.ports.inbound;

import java.util.List;

import com.marketplace.core.domain.model.Category;

public interface CategoryService {
	
	public Category create(Category model);
	public Category update(Category model);
	public Category findById(Long id);
	public List<Category> findAll();
	public boolean deleteById(Long id);
	
}

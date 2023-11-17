package com.marketplace.adapters.inbound.dto;

import com.marketplace.core.domain.model.Category;

public record CategoryDTO(Long id, String name) {

	public CategoryDTO(Category model) {
		this(model.getId(), model.getName());
	}

	public Category toModel() {
		Category model = new Category();
		model.setId(this.id);
		model.setName(this.name);
		return model;
	}
}

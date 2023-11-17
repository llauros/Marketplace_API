package com.marketplace.adapters.inbound.dto;

import com.marketplace.core.domain.model.SubCategory;

public record SubCategoryDTO(Long id, String name, CategoryDTO categoryDTO) {

	public SubCategoryDTO(SubCategory model) {
		this(model.getId(), model.getName(), new CategoryDTO(model.getCategory()));
	}

	public SubCategory toModel() {
		SubCategory model = new SubCategory();
		model.setId(this.id);
		model.setName(this.name);
		model.setCategory(this.categoryDTO.toModel());
		return model;
	}
}

package com.marketplace.adapters.inbound.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.marketplace.core.domain.model.Product;

import jakarta.validation.constraints.NotEmpty;

public record ProductDTO(
		@NotEmpty(message = "name must not be empty")
		@Length(min = 3, max = 100, message = "name must have a minimum of 3 characters and a maximum of 100")
		String name,
		String description,
		BigDecimal price,
		String photo
//		@NotNull(message = "business must not be null")
//		BusinessDTO business,
//		Set<SubCategoryDTO> subCategories
		) {

    public ProductDTO(Product model) {
    	this(model.getName(),
    		model.getDescription(),
    		model.getPrice(),
    		model.getPhoto()
//    		new BusinessDTO(model.getBusiness()),
//    		model.getSubCategories().stream().map(a -> new SubCategoryDTO(a)).collect(Collectors.toSet())
    		);
    }
	
	public Product toModel() {
		Product model = new Product();
		model.setName(this.name);
		model.setDescription(this.description);
		model.setPrice(this.price);
		model.setPhoto(this.photo);
//		model.setBusiness(this.business.toModel());
//		
//		if(this.subCategories != null)
//			model.setSubCategories(this.subCategories.stream().map(a -> a.toModel()).collect(Collectors.toSet()));
		
		return model;
	}
}

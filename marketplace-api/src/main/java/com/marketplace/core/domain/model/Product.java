package com.marketplace.core.domain.model;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private String photo;
	private Set<SubCategory> subCategories;
	private Business business;	
}


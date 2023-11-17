package com.marketplace.core.domain.model;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

	private Long id;
	private String name;
	private Set<SubCategory> subCategories;

}

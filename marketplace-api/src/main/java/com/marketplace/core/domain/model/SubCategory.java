package com.marketplace.core.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCategory {
	
	private Long id;
	private String name;
	private Category category;
	
}

package com.payment.manager.domain.core.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

	private Long id;
	private String productCode;
	private String name;
	private String description;
	private BigDecimal price;

}

package com.payment.manager.domain.core.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {

	private String cartCode;
	private User user;
	private List<CartItem> cardItems;
	
}

package com.payment.manager.adapter.inbound.dto;

import com.payment.manager.domain.core.model.Cart;
import com.payment.manager.domain.core.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {

	private String cartCode;
	private Long userId;
	
	public Cart toModel() {
		Cart model = new Cart();
		model.setUser(new User(userId));
		model.setCartCode(cartCode);
		return model;
	}

}

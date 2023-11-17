package com.payment.manager.domain.core.service;

import com.payment.manager.domain.core.model.Cart;

public interface CartService {

	public Cart findCart(Cart cart);
	public void addItem();
	public void removeItem();
	public void clearCart();
}

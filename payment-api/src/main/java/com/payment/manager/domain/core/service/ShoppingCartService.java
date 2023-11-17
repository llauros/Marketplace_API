package com.payment.manager.domain.core.service;

import java.math.BigDecimal;

public interface ShoppingCartService {

//	void addItem(Product product, int quantity);
//	void removeItem(Product product);
//	void updateQuantity(Product product, int newQuantity);
	void clearCart();
	BigDecimal getTotal();
	
}

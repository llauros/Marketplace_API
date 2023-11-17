package com.payment.manager.domain.port.in;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.manager.adapter.outbound.entity.CartEntity;
import com.payment.manager.adapter.outbound.entity.UserEntity;
import com.payment.manager.adapter.outbound.repository.CartItemRepository;
import com.payment.manager.adapter.outbound.repository.CartRepository;
import com.payment.manager.domain.core.model.Cart;
import com.payment.manager.domain.core.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	public Cart findCart(Cart cart) {
		Optional<CartEntity> a = cartRepository.findCartByUser(new UserEntity(cart.getUser()));
		return a.get().toModel();
	}
	
	public void addItem() {

	}
	
	public void removeItem() {

	}

	@Override
	public void clearCart() {
		// TODO Auto-generated method stub
		
	}
	
	private Optional<String> containsProduct() {
		
		return null;
	}

}

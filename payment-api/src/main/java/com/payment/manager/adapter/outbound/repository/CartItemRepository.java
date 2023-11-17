package com.payment.manager.adapter.outbound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.manager.adapter.outbound.entity.CartEntity;
import com.payment.manager.adapter.outbound.entity.CartItemEntity;
import com.payment.manager.adapter.outbound.entity.ProductEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

	List<CartItemEntity> findCartItemByCart(CartEntity cart);
	
	Optional<CartItemEntity> findCartItemByProduct(ProductEntity product);
	
}

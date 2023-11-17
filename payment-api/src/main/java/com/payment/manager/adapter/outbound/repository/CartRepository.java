package com.payment.manager.adapter.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.payment.manager.adapter.outbound.entity.CartEntity;
import com.payment.manager.adapter.outbound.entity.UserEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

	@Query("SELECT c FROM CartEntity c LEFT JOIN FETCH c.cartItems WHERE c.user = :user OR c.cartCode = :cartCode")
	Optional<CartEntity> findByUserOrCartCode(@Param("user") UserEntity user, @Param("cartCode") String cartCode);
	Optional<CartEntity> findByCartCode(String cartCode);
	Optional<CartEntity> findCartByUser(UserEntity user);
	
}

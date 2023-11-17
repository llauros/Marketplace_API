package com.payment.manager.adapter.inbound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.manager.adapter.inbound.dto.CartRequest;
import com.payment.manager.domain.core.model.Cart;
import com.payment.manager.domain.core.service.CartService;

@SuppressWarnings({ "rawtypes", "unchecked" })

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService service;
	
	
	@GetMapping
	public ResponseEntity<Cart> getCart(@RequestBody CartRequest cartRequest) {
		
		//service.findCart(cartRequest.toModel());
		
		return new ResponseEntity(service.findCart(cartRequest.toModel()),  HttpStatus.CREATED);
	}
	
	@PostMapping("/item")
	public ResponseEntity<String> addItem() {
		return null;
	}
	
	@DeleteMapping("/item/{itemId}")
	public ResponseEntity<String> removeItem() {
		return null;
	}
	
	@DeleteMapping
	public ResponseEntity<String> clearCart() {
		return null;
	}
}

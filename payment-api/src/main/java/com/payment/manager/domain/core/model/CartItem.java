package com.payment.manager.domain.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
	
    private Product product;
    private int quantity;
    
}

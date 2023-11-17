package com.marketplace.core.ports.inbound;

import java.util.List;

import com.marketplace.adapters.outbound.entity.OrderEntity;
import com.marketplace.core.domain.model.Order;

public interface OrderService {
	
	public List<Order> findAll();
	public OrderEntity findById(Long id);
	public Order create(Order model);
	public Order update(Order model);
	public boolean deleteById(Long id);
    
}

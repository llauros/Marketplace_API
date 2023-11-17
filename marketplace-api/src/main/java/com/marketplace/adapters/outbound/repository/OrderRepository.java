package com.marketplace.adapters.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.adapters.outbound.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}

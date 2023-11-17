package com.payment.manager.config.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.manager.adapter.outbound.entity.ProductEntity;

public interface ProductRepositoryTemp extends JpaRepository<ProductEntity, Long> {

}

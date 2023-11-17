package com.marketplace.adapters.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.adapters.outbound.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

}

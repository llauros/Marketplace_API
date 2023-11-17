package com.marketplace.adapters.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.adapters.outbound.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

}

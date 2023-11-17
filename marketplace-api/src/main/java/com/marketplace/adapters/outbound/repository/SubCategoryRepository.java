package com.marketplace.adapters.outbound.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marketplace.adapters.outbound.entity.SubCategoryEntity;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long>{

	@Query("SELECT u FROM SubCategoryEntity u WHERE u.id IN :subCategoryList" )
	Set<SubCategoryEntity> findSubCategoryById(@Param("subCategoryList") List<Long> list);
	
}
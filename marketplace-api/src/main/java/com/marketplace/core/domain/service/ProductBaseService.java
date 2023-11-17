package com.marketplace.core.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.adapters.outbound.entity.BusinessEntity;
import com.marketplace.adapters.outbound.entity.ProductEntity;
import com.marketplace.adapters.outbound.entity.SubCategoryEntity;
import com.marketplace.adapters.outbound.repository.BusinessRepository;
import com.marketplace.adapters.outbound.repository.ProductRepository;
import com.marketplace.adapters.outbound.repository.SubCategoryRepository;
import com.marketplace.core.domain.model.Product;
import com.marketplace.core.ports.inbound.ProductService;

@Service
public class ProductBaseService implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private BusinessRepository businessRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public Product create(Product model) {

		if (model != null && model.getBusiness() != null && model.getSubCategories() != null) {

			Optional<BusinessEntity> businessResult = businessRepository.findById(model.getBusiness().getId());
			Set<SubCategoryEntity> subCategoriesResults = subCategoryRepository.findSubCategoryById(
					model.getSubCategories().stream().map(a -> a.getId()).collect(toList()));

			if (businessResult.isPresent() && !subCategoriesResults.isEmpty()) {
				ProductEntity entity = new ProductEntity(model);
				entity.setBusiness(businessResult.get());
				entity.setSubCategories(subCategoriesResults);

				return repository.save(entity).toModel();
			}
		}

		return null;
	}

	@Override
	public Product update(Product model) {

		return repository.findById(model.getId()).map(result -> {

			result.setName(model.getName());
			result.setDescription(model.getDescription());
			result.setPrice(model.getPrice());
			result.setPhoto(model.getPhoto());

			result.setBusiness(new BusinessEntity(model.getBusiness()));

			return repository.save(result).toModel();
		}).orElseGet(() -> {
			return null;
		});

	}

	@Override
	public Product findById(Long id) {
		return repository.findById(id).map(ProductEntity::toModel).orElse(null);
	}

	@Override
	public List<Product> findAll() {
		return repository.findAll().stream().map(ProductEntity::toModel).collect(toList());
	}

	@Override
	public boolean deleteById(Long id) {
		return repository.findById(id).map(a -> {
			repository.deleteById(id);
			return true;
		}).orElse(false);
	}

	/*
	 * private void findBusinessAndSubCategoryById(Business business,
	 * Set<SubCategory> subCategories) {
	 * 
	 * Optional<BusinessEntity> businessResult =
	 * businessRepository.findById(business.getId()); Set<SubCategoryEntity>
	 * subCategoriesResults = subCategoryRepository .findSubCategoryById(
	 * subCategories .stream().map(a -> a.getId()) .collect(Collectors.toList()));
	 * 
	 * if(businessResult.isPresent() && !subCategoriesResults.isEmpty()){
	 * 
	 * } }
	 */

}
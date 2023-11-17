package com.marketplace.core.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.adapters.outbound.entity.CategoryEntity;
import com.marketplace.adapters.outbound.entity.SubCategoryEntity;
import com.marketplace.adapters.outbound.repository.CategoryRepository;
import com.marketplace.adapters.outbound.repository.SubCategoryRepository;
import com.marketplace.core.domain.model.SubCategory;
import com.marketplace.core.ports.inbound.SubCategoryService;

@Service
public class SubCategoryBaseService implements SubCategoryService {

	@Autowired
	private SubCategoryRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public SubCategory create(SubCategory model) {

		if (model != null && model.getCategory() != null) {

			Optional<CategoryEntity> categoryResult = categoryRepository.findById(model.getCategory().getId());

			if (categoryResult.isPresent()) {

				SubCategoryEntity entity = new SubCategoryEntity(model);
				entity.setCategory(categoryResult.get());

				return repository.save(entity).toModel();
			}
		}
		return null;
	}

	@Override
	public SubCategory update(SubCategory model) {

		return repository.findById(model.getId()).map(result -> {

			result.setName(model.getName());

			return repository.save(result).toModel();
		}).orElseGet(() -> {
			return null;
		});

	}

	@Override
	public SubCategory findById(Long id) {
		Optional<SubCategoryEntity> entity = repository.findById(id);

		if (entity.isPresent()) {
			return entity.get().toModel();
		}
		return null;
	}

	@Override
	public List<SubCategory> findAll() {
		List<SubCategoryEntity> entity = this.repository.findAll();

		if (entity != null && !entity.isEmpty()) {
			return entity.stream().map(item -> item.toModel()).collect(Collectors.toList());
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteById(Long id) {
		Optional<SubCategoryEntity> result = repository.findById(id);
		if (result.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
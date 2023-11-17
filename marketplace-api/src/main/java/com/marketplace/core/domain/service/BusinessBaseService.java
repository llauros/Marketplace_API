package com.marketplace.core.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marketplace.adapters.outbound.entity.BusinessEntity;
import com.marketplace.adapters.outbound.entity.UserEntity;
import com.marketplace.adapters.outbound.repository.BusinessRepository;
import com.marketplace.adapters.outbound.repository.UserRepository;
import com.marketplace.core.domain.model.Business;
import com.marketplace.core.ports.inbound.BusinessService;

@Service
public class BusinessBaseService implements BusinessService {

	@Autowired
	private BusinessRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Business create(Business newBusiness) {

		if (newBusiness.getOwnerUser() != null) {

			Optional<BusinessEntity> validateExistingBusiness = repository
					.findBusinessByOwnerUser(newBusiness.getOwnerUser().getId());
			
			if (validateExistingBusiness.isEmpty()) {

				Optional<UserEntity> result = userRepository.findById(newBusiness.getOwnerUser().getId());

				if (result.isPresent()) {
					BusinessEntity entity = new BusinessEntity(newBusiness);					
					entity.setOwnerUser(result.get());

					return repository.save(entity).toModel();
				}
			}
		}

		return null;
	}
	
	@Override
	public Business update(Business model) {

		return repository.findById(model.getId()).map(result -> {

			result.setName(model.getName());
			result.setDescription(model.getDescription());

			return repository.save(result).toModel();
		}).orElseGet(() -> {
			return null;
		});
	}
	
	@Override
	public Business findById(Long id) {
		return repository.findById(id).map(BusinessEntity::toModel).orElse(null);
	}
	
	@Override
	public Page<Business> findAll(Pageable pageable) {
		return repository.findAll(pageable).map(BusinessEntity::toModel);
	}

	@Override
	public boolean deleteById(Long id) {

		Optional<BusinessEntity> result = repository.findById(id);

		if (result.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

}

package com.marketplace.core.ports.inbound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.marketplace.core.domain.model.Business;

public interface BusinessService {
	
    public Business create(Business business);
    public Business update(Business business);
    public Business findById(Long id);
    public Page<Business> findAll(Pageable pageable);
    public boolean deleteById(Long id);
    
}

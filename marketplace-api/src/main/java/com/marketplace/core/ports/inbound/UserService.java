package com.marketplace.core.ports.inbound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.marketplace.core.domain.model.User;

public interface UserService {
	
    public User create(User user);
    public User update(User user);
    public User findById(Long id);
    public User findByEmail(String email);
	public Page<User> findByAttributes(String userEmail, String userName, Pageable pageable);
    public boolean deleteById(Long id);
    
}
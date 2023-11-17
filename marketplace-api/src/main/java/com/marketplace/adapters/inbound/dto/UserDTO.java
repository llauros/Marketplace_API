package com.marketplace.adapters.inbound.dto;

import org.hibernate.validator.constraints.Length;

import com.marketplace.core.domain.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
		Long id,
		@NotNull(message = "name must not be null")
		@NotEmpty(message = "name must not be empty")
		@Length(min = 3, max = 100, message = "name must have a minimum of 3 characters and a maximum of 100")
		String name,
		@Email
		@NotNull(message = "email must not be null")
		String email,
		@NotEmpty(message = "password must not be empty")
		@NotNull(message = "password must not be null")
		@Length(min = 8, max = 20, message = "password must have a minimum of 8 characters and a maximum of 20")
		String password) {

    public UserDTO(User model) {
    	this(model.getId(),
    		model.getName(),
    		model.getEmail(),
    		model.getPassword());
    }
	
	public User toModel() {
		User model = new User();
		model.setId(this.id);
		model.setName(this.name);
		model.setEmail(this.email);
		model.setPassword(this.password);
		return model;
	}
}

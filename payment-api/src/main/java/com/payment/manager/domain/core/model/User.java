package com.payment.manager.domain.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
	
	private Long id;
	private String email;
	
	public User(Long id) {
		this.id = id;
	}
	
}
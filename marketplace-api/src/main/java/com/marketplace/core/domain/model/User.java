package com.marketplace.core.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
	private Long id;
	private String name;
	private String email;
	private String password;
}
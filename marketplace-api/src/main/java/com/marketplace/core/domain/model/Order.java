package com.marketplace.core.domain.model;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

	private Long id;
	private Instant moment;
	private User userClient;
	
}

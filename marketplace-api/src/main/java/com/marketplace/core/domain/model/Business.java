package com.marketplace.core.domain.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Business {

	private Long id;
	private String name;
	private String description;
	private User ownerUser;
	private List<Product> products;
	
//	private User ownerUser; //TODO Completar a Classe
//	private String profilePicture;
//	private String bannerImage;
//	private Date createdAt;
//	private Date updatedAt;
	
}


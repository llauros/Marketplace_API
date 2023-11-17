package com.marketplace.adapters.inbound.dto;

import com.marketplace.core.domain.model.Business;

public record BusinessDTO(Long id, String name, String description, UserDTO ownerUserDTO) {

	public BusinessDTO(Business model) {
		this(model.getId(), model.getName(), model.getDescription(), new UserDTO(model.getOwnerUser()));
	}

	public Business toModel() {
		Business model = new Business();
		model.setId(this.id);
		model.setName(this.name);
		model.setDescription(this.description);
		
		if(this.ownerUserDTO != null)
			model.setOwnerUser(this.ownerUserDTO.toModel());
		
		return model;
	}
}

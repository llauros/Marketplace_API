package com.marketplace.adapters.outbound.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marketplace.core.domain.model.SubCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_subcategoria")
public class SubCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 100)
	private String name;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private CategoryEntity category;

	@JsonIgnore
	@ManyToMany(mappedBy = "subCategories")
	private Set<ProductEntity> products = new HashSet<>();

	public SubCategoryEntity(SubCategory model) {
		if (model != null && model.getCategory() != null) {
			this.name = model.getName();
			this.category = new CategoryEntity(model.getCategory());
		}
	}

	public SubCategoryEntity(String name, CategoryEntity category) {
		this.name = name;
		this.category = category;
	}

	public SubCategory toModel() {
		SubCategory model = new SubCategory();

		model.setId(this.id);
		model.setName(this.name);

		return model;
	}
}


package com.marketplace.adapters.outbound.entity;

import static java.util.stream.Collectors.toSet;

import java.util.HashSet;
import java.util.Set;

import com.marketplace.core.domain.model.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_categoria")
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 100)
	private String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<SubCategoryEntity> subCategories = new HashSet<>();

	public CategoryEntity(@NonNull Category model) {

		this.id = model.getId();
		this.name = model.getName();

		if (model.getSubCategories() != null)
			this.subCategories = model.getSubCategories().stream().map(a -> new SubCategoryEntity(a)).collect(toSet());
	}

	public CategoryEntity(String name) {
		this.name = name;
	}

	public Category toModel() {
		Category model = new Category();

		model.setId(this.id);
		model.setName(this.name);

		if (this.subCategories != null)
			model.setSubCategories(this.subCategories.stream().map(a -> a.toModel()).collect(toSet()));

		return model;
	}
}


package com.marketplace.adapters.outbound.entity;

import static java.util.stream.Collectors.toSet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.marketplace.core.domain.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "tb_produto")
public class ProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 100)
	private String name;

	@Column(name = "descricao", length = 500)
	private String description;

	@Column(name = "preco")
	private BigDecimal price;

	@Column(name = "foto")
	private String photo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_produto_subcategoria", joinColumns =
		@JoinColumn(name = "id_produto"), inverseJoinColumns =
		@JoinColumn(name = "id_subcategoria"))
	private Set<SubCategoryEntity> subCategories = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "negocio_id")
	private BusinessEntity business;

	public ProductEntity(Product model) {
		this.name = model.getName();
		this.description = model.getDescription();
		this.price = model.getPrice();
		this.photo = model.getPhoto();

		if (model.getBusiness() != null)
			this.business = new BusinessEntity(model.getBusiness());

		if (model.getSubCategories() != null) {
			this.subCategories = model.getSubCategories().stream().map(a -> new SubCategoryEntity(a)).collect(toSet());
		}
	}

	public ProductEntity(String name, String description, BigDecimal price, String photo, BusinessEntity business) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.photo = photo;
		this.business = business;
	}

	public Product toModel() {
		Product model = new Product();
		model.setId(this.id);
		model.setName(this.name);
		model.setDescription(this.description);
		model.setPrice(this.price);
		model.setPhoto(this.photo);
		return model;
	}
	
	public Product toModelWithAssociatedEntities() {
		Product model = new Product();
		model.setId(this.id);
		model.setName(this.name);
		model.setDescription(this.description);
		model.setPrice(this.price);
		model.setPhoto(this.photo);
		
		if (this.subCategories != null)
			model.setSubCategories(this.subCategories.stream().map(a -> a.toModel()).collect(toSet()));
		
		if (this.business != null)
			model.setBusiness(this.business.toModel());

		return model;
	}
	
	
}


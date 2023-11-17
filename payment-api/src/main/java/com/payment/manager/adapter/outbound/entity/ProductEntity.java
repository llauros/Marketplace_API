package com.payment.manager.adapter.outbound.entity;


import java.io.Serializable;
import java.math.BigDecimal;

import com.payment.manager.domain.core.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

//	public ProductEntity(Product model) {
//		this.name = model.getName();
//		this.description = model.getDescription();
//		this.price = model.getPrice();
//		this.photo = model.getPhoto();
//	}
//
	public Product toModel() {
		Product model = new Product();
		model.setId(this.id);
		model.setName(this.name);
		model.setDescription(this.description);
		model.setPrice(this.price);
		return model;
	}
	
}

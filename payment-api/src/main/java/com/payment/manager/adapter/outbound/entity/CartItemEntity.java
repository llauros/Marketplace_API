package com.payment.manager.adapter.outbound.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payment.manager.domain.core.model.CartItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_carrinho_produtos")
public class CartItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    @JsonIgnore
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProductEntity product;

    @Column(name = "quantidade")
    private int quantity;

	public CartItemEntity(CartEntity cart, ProductEntity product, int quantity) {
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}
    
	public CartItem toModel() {
		CartItem model = new CartItem();
		model.setProduct(this.product.toModel());
		model.setQuantity(this.quantity);
		return model;
	}
}

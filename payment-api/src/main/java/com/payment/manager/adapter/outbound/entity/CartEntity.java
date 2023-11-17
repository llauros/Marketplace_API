package com.payment.manager.adapter.outbound.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.payment.manager.domain.core.model.Cart;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "tb_carrinho")
public class CartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItems = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UserEntity user;

    @Column(name = "codigo_carrinho", unique = true)
    private String cartCode;

	public CartEntity(UserEntity user, String cartCode) {
		this.user = user;
		this.cartCode = cartCode;
	}
    
	public Cart toModel() {
		Cart model = new Cart();
		model.setUser(user.toModel());
		model.setCardItems(cartItems.stream().map( a -> a.toModel() ).toList() );
		return model;
	}
}

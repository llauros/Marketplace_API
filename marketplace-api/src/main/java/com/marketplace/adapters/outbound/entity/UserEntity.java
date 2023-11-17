package com.marketplace.adapters.outbound.entity;

import java.io.Serializable;

import com.marketplace.core.domain.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tb_usuario")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String password;

	public UserEntity(@NonNull User model) {
		this.id = model.getId();
		this.name = model.getName();
		this.email = model.getEmail();
		this.password = model.getPassword();
	}

	public UserEntity(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User toModel() {
		User model = new User();
		model.setId(this.id);
		model.setName(this.name);
		model.setEmail(this.email);
		model.setPassword(this.password);
		return model;
	}
}
package com.payment.manager.adapter.outbound.entity;

import java.io.Serializable;

import com.payment.manager.domain.core.model.User;

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
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "tb_usuario")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email")
	private String email;

	public UserEntity(User model) {
		this.id = model.getId();
		this.email = model.getEmail();
	}

	public UserEntity(String email) {
		this.email = email;
	}

	public User toModel() {
		User model = new User();
		model.setId(this.id);
		model.setEmail(this.email);
		return model;
	}
}

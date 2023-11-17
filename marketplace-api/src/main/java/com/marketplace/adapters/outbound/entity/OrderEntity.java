package com.marketplace.adapters.outbound.entity;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marketplace.core.domain.model.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "tb_pedido")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Column(name = "data")
	private Instant moment;

	public OrderEntity(Order model) {
		this.moment = model.getMoment(); 	
	}
	
	public OrderEntity(Instant moment) {
		this.moment = moment;
	}

	public Order toModel() {
		Order model = new Order();
		
		model.setId(this.id);
		model.setMoment(this.moment);
		
		return model;
	}
}

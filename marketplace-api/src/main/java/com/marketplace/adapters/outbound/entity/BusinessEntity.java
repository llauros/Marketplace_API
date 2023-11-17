package com.marketplace.adapters.outbound.entity;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;

import com.marketplace.core.domain.model.Business;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_negocio")
public class BusinessEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String name;

	@Column(name = "descricao")
	private String description;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private UserEntity ownerUser;
	
	@OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
	private List<ProductEntity> products;
	
//	@Column(name = "foto_perfil") TODO
//	private String profilePicture;
//	@Column(name = "banner")
//	private String bannerImage;
//	@Column(name = "senha")
//	private Date createdAt;
//	@Column(name = "senha")
//	private Date updatedAt;
	
	public BusinessEntity(Business model) {
		if(model != null && model.getOwnerUser() != null) {
			this.name = model.getName();
			this.description = model.getDescription();
			 
			this.ownerUser = new UserEntity(model.getOwnerUser());		
			
			if(model.getProducts() != null)
				this.products = model.getProducts().stream().map(a -> new ProductEntity(a)).collect(toList());
		}
	}

	public BusinessEntity(String name, String description, UserEntity ownerUser) {
		this.name = name;
		this.description = description;
		this.ownerUser = ownerUser;
	}

	public Business toModel() {
		Business model = new Business();
		
		model.setId(this.id);
		model.setName(this.name);
		model.setDescription(this.description);
		
		model.setOwnerUser(this.ownerUser.toModel());
		
		//TODO Criar "sobrecarga" de toModel para trazer produtos
		/*if(this.products != null)
			model.setProducts(this.products.stream().map(a -> a.toModel()).collect(toList()));*/
		
		return model;
	}

}


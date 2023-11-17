package com.payment.manager.config.database;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.payment.manager.adapter.outbound.entity.CartEntity;
import com.payment.manager.adapter.outbound.entity.CartItemEntity;
import com.payment.manager.adapter.outbound.entity.ProductEntity;
import com.payment.manager.adapter.outbound.entity.UserEntity;
import com.payment.manager.adapter.outbound.repository.CartItemRepository;
import com.payment.manager.adapter.outbound.repository.CartRepository;

@Component
@Configuration
public class DatabaseSeeding implements CommandLineRunner {

	@Autowired
	private UserRepositoryTemp userRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepositoryTemp productRepository;
	
	@Autowired
	private CartItemRepository cartItemsRepository;

	@Override
	public void run(String... args) throws Exception {

//		List<UserEntity> usuarios = dbsUsers();
//		List<CartEntity> carrinhos = dbsCarts(usuarios);
//		dbsCartItems(carrinhos, findAllProduct());

//		UserEntity a = new UserEntity();
//		a.setId(10l);
//		System.out.println(a);
//		CartEntity b = cartRepository.findByUserOrCartCode(a, "").get();
//		System.out.println( b );
	}

	public List<UserEntity> dbsUsers() {
		UserEntity u1 = new UserEntity("roberta@email.com");
		UserEntity u2 = new UserEntity("pedro_riacho@email.com");
		UserEntity u3 = new UserEntity("djavan@email.com");
		return userRepository.saveAll(Arrays.asList(u1, u2, u3));
	}

	public List<CartEntity> dbsCarts(List<UserEntity> usuarios) {
		CartEntity c1 = new CartEntity(usuarios.get(0), "ABC");
		CartEntity c2 = new CartEntity(usuarios.get(1), "DEF");
		CartEntity c3 = new CartEntity(usuarios.get(2), "GHI");
		return cartRepository.saveAll(Arrays.asList(c1, c2, c3));
	}

	public List<CartItemEntity> dbsCartItems(List<CartEntity> carrinhos, List<ProductEntity> produtos) {
		CartItemEntity cI1 = new CartItemEntity(carrinhos.get(0), produtos.get(0), 5);
		CartItemEntity cI2 = new CartItemEntity(carrinhos.get(1), produtos.get(1), 3);
		CartItemEntity cI3 = new CartItemEntity(carrinhos.get(1), produtos.get(2), 2);
		return cartItemsRepository.saveAll(Arrays.asList(cI1, cI2, cI3));
	}
	
	public List<ProductEntity> findAllProduct() {
		return productRepository.findAll();
	}
}

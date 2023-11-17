package com.marketplace.config.database;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.marketplace.adapters.outbound.entity.BusinessEntity;
import com.marketplace.adapters.outbound.entity.CategoryEntity;
import com.marketplace.adapters.outbound.entity.ProductEntity;
import com.marketplace.adapters.outbound.entity.SubCategoryEntity;
import com.marketplace.adapters.outbound.entity.UserEntity;
import com.marketplace.adapters.outbound.repository.BusinessRepository;
import com.marketplace.adapters.outbound.repository.CategoryRepository;
import com.marketplace.adapters.outbound.repository.ProductRepository;
import com.marketplace.adapters.outbound.repository.SubCategoryRepository;
import com.marketplace.adapters.outbound.repository.UserRepository;

@Component
@Configuration
public class DatabaseSeeding implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BusinessRepository businessRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private ProductRepository productRepository;
//	@Autowired private OrderRepository orderRepository;
//	@Autowired private OrderProductRepository orderProductRepository;

	@Override
	public void run(String... args) throws Exception {

		List<UserEntity> usuarios = dbsUsers();
		List<BusinessEntity> negocios = dbsBusiness(usuarios);	
		List<CategoryEntity> cetegorias = dbsCategory();
		List<SubCategoryEntity> subCategorias = dbsSubCategory(cetegorias);	
		List<ProductEntity> produtos = dbsProduct(negocios, subCategorias);

//		List<OrderEntity> pedidos = dbsOrders(usuarios);
//		dbsOrderProduct(pedidos, produtos);
	}

//	private List<OrderEntity> dbsOrders(List<UserEntity> users) {
//		OrderEntity o1 = new OrderEntity(Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.CANCELED, users.get(0));
//		OrderEntity o2 = new OrderEntity(Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAID, users.get(1));
//		OrderEntity o3 = new OrderEntity(Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, users.get(2));
//		return orderRepository.saveAll(Arrays.asList(o1, o2, o3));
//	}
//	
//	private List<OrderProductEntity> dbsOrderProduct(List<OrderEntity> orders, List<ProductEntity> products ) {
//		OrderProductEntity op1 = new OrderProductEntity(orders.get(0), products.get(0), 2, products.get(0).getPrice());
//		OrderProductEntity op2 = new OrderProductEntity(orders.get(0), products.get(1), 1, products.get(1).getPrice());
//		OrderProductEntity op3 = new OrderProductEntity(orders.get(1), products.get(2), 2, products.get(2).getPrice());
//		return orderProductRepository.saveAll(Arrays.asList(op1, op2, op3));
//	}

	public List<UserEntity> dbsUsers() {
		String p = "senha";
		UserEntity u1 = new UserEntity("Henzo Alpuim", "henzo@email.com", p);
		UserEntity u2 = new UserEntity("Jayden Lagoa", "jayden@email.com", p);
		UserEntity u3 = new UserEntity("Erica Santarem", "erica@email.com", p);
		return userRepository.saveAll(Arrays.asList(u1, u2, u3));
	}

	public List<BusinessEntity> dbsBusiness(List<UserEntity> usuarios) {
		BusinessEntity b1 = new BusinessEntity("Games Eduu", "Conheça a melhor loja de Games", usuarios.get(0));
		BusinessEntity b2 = new BusinessEntity("Americanas", "Tem tudo", usuarios.get(1));
		BusinessEntity b3 = new BusinessEntity("Melissa Cosméticos", "Perfume-se", usuarios.get(2));
		return businessRepository.saveAll(Arrays.asList(b1, b2, b3));
	}

	public List<CategoryEntity> dbsCategory() {
		CategoryEntity c1 = new CategoryEntity("Moda");
		CategoryEntity c2 = new CategoryEntity("Eletrônicos");
		CategoryEntity c3 = new CategoryEntity("Alimentos & Bebidas");
		return categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
	}

	public List<SubCategoryEntity> dbsSubCategory(List<CategoryEntity> cetegorias) {
		SubCategoryEntity s1 = new SubCategoryEntity("Moda Feminina", cetegorias.get(0));
		SubCategoryEntity s2 = new SubCategoryEntity("Moda Masculina", cetegorias.get(0));
		SubCategoryEntity s3 = new SubCategoryEntity("Smart", cetegorias.get(1));
		// SubCategoryEntity s4 = new SubCategoryEntity("Pãezinhos", cetegorias.get(2));
		return subCategoryRepository.saveAll(Arrays.asList(s1, s2, s3));
	}

	public List<ProductEntity> dbsProduct(List<BusinessEntity> negocios, List<SubCategoryEntity> subcategorias) {
		ProductEntity p1 = new ProductEntity("Camisa Naruto", "Naruto Shippuden", new BigDecimal("90.5"), null, negocios.get(0));
		p1.getSubCategories().add(subcategorias.get(1));
		p1.getSubCategories().add(subcategorias.get(0));
		ProductEntity p2 = new ProductEntity("Macbook Pro", "Nam eleifend maximus tortor", new BigDecimal("1250.0"), null, negocios.get(0));
		p2.getSubCategories().add(subcategorias.get(2));
		ProductEntity p3 = new ProductEntity("Redmi X3", "The better smartphone.", new BigDecimal("800.99"), null, negocios.get(0));
		p3.getSubCategories().add(subcategorias.get(2));
		ProductEntity p4 = new ProductEntity("Hidratante", "Neutrogena matte 3 em 1", new BigDecimal("20.33"), null, negocios.get(0));
		p4.getSubCategories().add(subcategorias.get(0));
		return productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
	}
}
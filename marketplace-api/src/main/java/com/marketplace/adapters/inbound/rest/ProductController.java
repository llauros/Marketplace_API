package com.marketplace.adapters.inbound.rest;

import java.util.List;
import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.adapters.inbound.dto.ProductDTO;
import com.marketplace.core.domain.model.Product;
import com.marketplace.core.ports.inbound.ProductService;

import jakarta.validation.Valid;

@SuppressWarnings({ "rawtypes", "unchecked" })

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO productDTO) {

		Product model = service.create(productDTO.toModel());

		return model == null ?
				new ResponseEntity(HttpStatus.BAD_REQUEST) :
				new ResponseEntity<ProductDTO>(new ProductDTO(model), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {

		Product user = productDTO.toModel();
		user.setId(id);

		Product result = service.update(user);

		return result == null ?
				new ResponseEntity(HttpStatus.NO_CONTENT) :
				new ResponseEntity(new ProductDTO(result), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		Product result = service.findById(id);

		return result == null ?
				new ResponseEntity(HttpStatus.NO_CONTENT) :
				new ResponseEntity(new ProductDTO(result), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<Product> result = service.findAll();

		return new ResponseEntity(result.stream().map(ProductDTO::new).collect(toList()), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		return !service.deleteById(id) ?
				new ResponseEntity(HttpStatus.NOT_FOUND) :
				new ResponseEntity(HttpStatus.OK);
	}
}

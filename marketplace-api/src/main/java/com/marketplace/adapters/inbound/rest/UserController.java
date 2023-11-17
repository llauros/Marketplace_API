package com.marketplace.adapters.inbound.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.adapters.inbound.dto.UserDTO;
import com.marketplace.core.domain.model.User;
import com.marketplace.core.ports.inbound.UserService;

import jakarta.validation.Valid;

@SuppressWarnings({ "rawtypes", "unchecked" })

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO userDTO) {

		User model = service.create(userDTO.toModel());

		if (model == null) 
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<UserDTO>(new UserDTO(model), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {

		User user = userDTO.toModel();
		user.setId(id);

		User result = service.update(user);

		if (result == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity(new UserDTO(result), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		User result = this.service.findById(id);

		if (result == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity(new UserDTO(result), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<UserDTO> findByAttributes(
			@RequestParam(value = "user-email", required = false) String userEmail,
			@RequestParam(value = "user-name", required = false) String userName,
			@PageableDefault(page = 0, size = 20) Pageable pageable) {

		Page<User> result = this.service.findByAttributes(userEmail, userName, pageable);

		if (result == null)
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity(result.map(a -> new UserDTO(a)), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		boolean result = this.service.deleteById(id);
		
		if (!result)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity(HttpStatus.OK);
	}
}

package com.marketplace.adapters.inbound.rest;

import static java.util.stream.Collectors.toList;

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
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.adapters.inbound.dto.BusinessDTO;
import com.marketplace.core.domain.model.Business;
import com.marketplace.core.ports.inbound.BusinessService;

@SuppressWarnings({ "rawtypes", "unchecked" })

@RestController
@RequestMapping("/business")
public class BusinessController {

	@Autowired
	private BusinessService service;

	@PostMapping
	public ResponseEntity<BusinessDTO> create(@RequestBody BusinessDTO parameter) {

		Business model = this.service.create(parameter.toModel());

		return model == null ?
				new ResponseEntity(HttpStatus.BAD_REQUEST) :
				new ResponseEntity<BusinessDTO>(new BusinessDTO(model), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BusinessDTO> update(@PathVariable Long id, @RequestBody BusinessDTO parameter) {

		Business model = parameter.toModel();
		model.setId(id);

		Business result = this.service.update(model);

		return result == null ?
				new ResponseEntity(HttpStatus.NO_CONTENT) :
				new ResponseEntity(new BusinessDTO(result), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BusinessDTO> findById(@PathVariable Long id) {
		Business result = this.service.findById(id);

		return result == null ?
				new ResponseEntity(HttpStatus.NO_CONTENT) :
				new ResponseEntity(new BusinessDTO(result), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<BusinessDTO> findAll(@PageableDefault(page = 0, size = 20) Pageable pageable) {		
		Page<Business> result = service.findAll(pageable);

		return new ResponseEntity(result.stream().map(BusinessDTO::new).collect(toList()), HttpStatus.OK);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		boolean result = service.deleteById(id);

		return !result ?
				new ResponseEntity(HttpStatus.NOT_FOUND) :
				new ResponseEntity(HttpStatus.OK);
	}
}

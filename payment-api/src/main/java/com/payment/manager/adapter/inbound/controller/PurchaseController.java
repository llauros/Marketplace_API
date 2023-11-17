package com.payment.manager.adapter.inbound.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.manager.adapter.inbound.dto.PurchaseDetailsRequest;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@PostMapping
	public ResponseEntity<String> processPurchase(@RequestBody PurchaseDetailsRequest purchaseDetails) {
		System.out.println(purchaseDetails.getProd());
		return null;
	} 
}

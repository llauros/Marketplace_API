package com.payment.manager.domain.core.service;

import com.payment.manager.domain.core.model.PurchaseDetails;

public interface PurchaseService {

	void processPurchase(PurchaseDetails purchaseDetails);
	
}

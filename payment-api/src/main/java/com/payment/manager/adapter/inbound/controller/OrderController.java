package com.payment.manager.adapter.inbound.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mq.jakarta.jms.MQQueue;
import com.payment.manager.adapter.inbound.dto.OrderRequest;

import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@PostMapping
	public ResponseEntity<Object> createOrder(@RequestBody OrderRequest order) throws JMSException {
		log.info("Sending order message '{}' to the queue", order.getMessage());
		
		MQQueue orderRequestQueue = new MQQueue("DEV.QUEUE.2");
		jmsTemplate.convertAndSend(orderRequestQueue, order.getMessage(), textMessage -> {
			textMessage.setJMSCorrelationID(order.getIdentifier().toString());
			return textMessage;
		});
		
		return new ResponseEntity<Object>(order, HttpStatus.ACCEPTED);
	}
	
    @Deprecated // this was just to show how to find a message by correlation Id
    @GetMapping
    public ResponseEntity<OrderRequest> findOrderByCorrelationId(@RequestParam String correlationId) throws JMSException {
        log.info("Looking for message '{}'", correlationId);
        String convertedId = bytesToHex(correlationId.getBytes());
        final String selectorExpression = String.format("JMSCorrelationID='ID:%s'", convertedId);
        final TextMessage responseMessage = (TextMessage) jmsTemplate.receiveSelected("ORDER.REQUEST", selectorExpression);
        OrderRequest response = OrderRequest.builder()
                .message(responseMessage.getText())
                .identifier(correlationId)
                .build();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    // You could use Apache Commons Codec library instead
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes();
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
	
}

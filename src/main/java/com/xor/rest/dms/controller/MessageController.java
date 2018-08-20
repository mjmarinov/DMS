package com.xor.rest.dms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xor.rest.dms.exception.DMSBusinessException;
import com.xor.rest.dms.exception.DMSTechnicalException;
import com.xor.rest.dms.service.MessageService;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;
		
	@PostMapping(value = "/messages/{type}")
	public ResponseEntity<?> postMessage(@PathVariable(name = "type", required = true) String type,
			@RequestBody(required = false) String payload) {
	
		HttpStatus status;

		try {
			messageService.processMessage(type, payload);
			status = HttpStatus.CREATED;
		} catch (DMSBusinessException ex) {
			status = HttpStatus.PRECONDITION_FAILED;
		} catch (DMSTechnicalException ex) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return ResponseEntity.status(status).body(null);
	}

}

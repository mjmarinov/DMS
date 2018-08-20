package com.xor.rest.dms.service.impl;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.xor.rest.dms.exception.DMSBusinessException;
import com.xor.rest.dms.exception.DMSTechnicalException;
import com.xor.rest.dms.factory.MessageFactory;
import com.xor.rest.dms.model.AbstractMessage;
import com.xor.rest.dms.repository.MessageRepository;
import com.xor.rest.dms.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository repository;
	@Autowired
	private MessageFactory messageFactory;
	@Autowired
	private Validator validator;

	public void processMessage(String type, String payload) {

		AbstractMessage message = messageFactory.create(type);
		message.setPayload(payload);
		
		Set<ConstraintViolation<AbstractMessage>> violations = validator.validate(message);
		if(!violations.isEmpty()) {
			throw new DMSBusinessException("Constraint violation");
		}
		
		try {
			repository.save(message);
		}
		catch (DataAccessException ex) {
			throw new DMSTechnicalException(ex);
		}
	}

}

package com.xor.rest.dms.factory;

import org.springframework.stereotype.Component;

import com.xor.rest.dms.exception.DMSBusinessException;
import com.xor.rest.dms.model.AbstractMessage;
import com.xor.rest.dms.model.EmotionMessage;
import com.xor.rest.dms.model.MessageTypeEnum;
import com.xor.rest.dms.model.TextMessage;

@Component
public class MessageFactory {
	
	public AbstractMessage create(String messageType) {
		if (messageType == null)
			throw new DMSBusinessException("Message type cannot be null!");
		
		MessageTypeEnum mt = null;
		
		try {
			mt = MessageTypeEnum.valueOf(messageType.toUpperCase()); 
		} catch (IllegalArgumentException ex) {
			throw new DMSBusinessException("Invalid message type: " + String.valueOf(messageType));
		}
		
		return create(mt);
	}	

	public AbstractMessage create(MessageTypeEnum messageType) {
		
		if (messageType == null) {
			throw new DMSBusinessException("Message type cannot be null!");
		}
		
		switch (messageType) {
		case SEND_TEXT:
			return new TextMessage();
		case SEND_EMOTION:
			return new EmotionMessage();
		default: 
			return null;
		}
	}	
	
}

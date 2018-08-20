package com.xor.rest.dms.factory;

import org.springframework.stereotype.Component;

import com.xor.rest.dms.exception.DMSBusinessException;
import com.xor.rest.dms.model.AbstractMessage;
import com.xor.rest.dms.model.EmotionMessage;
import com.xor.rest.dms.model.TextMessage;

@Component
public class MessageFactory {
	
	public AbstractMessage create(String messageType) {
		
		if (messageType == null)
			throw new DMSBusinessException("Message type cannot be null!");

		switch (messageType.toLowerCase()) {
		case "send_text":
			return new TextMessage();
		case "send_emotion":
			return new EmotionMessage();
		default:
			throw new DMSBusinessException("Invalid message type: " + String.valueOf(messageType));
		}
	}	

}

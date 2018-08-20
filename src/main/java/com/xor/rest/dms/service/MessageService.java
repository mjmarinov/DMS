package com.xor.rest.dms.service;

public interface MessageService {

	void processMessage(String type, String payload);
	
}

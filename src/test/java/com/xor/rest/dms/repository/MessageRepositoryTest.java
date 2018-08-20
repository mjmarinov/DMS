package com.xor.rest.dms.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.xor.rest.dms.DMSApplication;
import com.xor.rest.dms.model.AbstractMessage;
import com.xor.rest.dms.model.TextMessage;
import com.xor.rest.dms.service.MessageService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DMSApplication.class)
@WebAppConfiguration
public class MessageRepositoryTest {
	
	@Autowired
	MessageService service;
	
	@Autowired
	MessageRepository repo;
	
	@Test
	public void shouldLogRequest() {
		final String payload = "Test payload";
		
		AbstractMessage message = new TextMessage();
		message.setPayload(payload);
		repo.save(message);
		
		Optional<AbstractMessage> loadedMessage = repo.findById(message.getId());
		
		assertNotNull(loadedMessage);
		assertTrue(loadedMessage.get() instanceof TextMessage);
		assertEquals(payload, loadedMessage.get().getPayload());
	}

}
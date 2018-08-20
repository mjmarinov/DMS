package com.xor.rest.dms.factory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.xor.rest.dms.DMSApplication;
import com.xor.rest.dms.exception.DMSBusinessException;
import com.xor.rest.dms.model.AbstractMessage;
import com.xor.rest.dms.model.EmotionMessage;
import com.xor.rest.dms.model.TextMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DMSApplication.class)
@WebAppConfiguration
public class MessageFactoryTest {

	@Autowired
	private MessageFactory factory;
	
	@Test(expected = DMSBusinessException.class)
	public void createInvalidMessageTest() {
		factory.create("fail");
	}
	
	@Test(expected = DMSBusinessException.class)
	public void createMessageWithEmptyTypeTest() {
		factory.create("");
	}
	
	@Test
	public void createTextMessageTest() {
		AbstractMessage actualMessage = factory.create("send_text");
		
		assertNotNull(actualMessage);
		assertTrue(actualMessage instanceof TextMessage);
	}
	
	@Test
	public void createEmotionMessageTest() {
		AbstractMessage actualMessage = factory.create("send_emotion");
		
		assertNotNull(actualMessage);
		assertTrue(actualMessage instanceof EmotionMessage);
	}
	
}

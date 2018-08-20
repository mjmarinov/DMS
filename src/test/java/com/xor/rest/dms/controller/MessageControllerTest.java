package com.xor.rest.dms.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.xor.rest.dms.DMSApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DMSApplication.class)
@WebAppConfiguration
public class MessageControllerTest {

	private static final String BASE_URL = "/messages";
    private static final String SEND_TEXT_URL = BASE_URL + "/send_text";
    private static final String SEND_EMOTION_URL = BASE_URL + "/send_emotion";
    private static final Integer MAX_TEXT_LENGTH = 160;
    private static final Integer MAX_EMOTION_LENGTH = 10;
	
	@Autowired
	private WebApplicationContext webContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	private String generateLongText(int length) {
		StringBuffer longTextBuffer = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			longTextBuffer.append("a");
		}
		
		return longTextBuffer.toString();
	}
	
	@Test
	public void sendTextEmptyBodyTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(SEND_TEXT_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(""))
		.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}

	@Test
	public void sendTextExceedLengthTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
				.post(SEND_TEXT_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(generateLongText(MAX_TEXT_LENGTH + 1)))
		.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}

	@Test
	public void sendTextCorrectTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(SEND_TEXT_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("This is a valid text message."))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void sendEmotionEmptyBodyTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(SEND_EMOTION_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(""))
		.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}

	@Test
	public void sendEmotionShortBodyTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(SEND_EMOTION_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("a"))
		.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}

	@Test
	public void sendEmotionExceedLengthTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
				.post(SEND_EMOTION_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(generateLongText(MAX_EMOTION_LENGTH + 1)))
		.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}

	@Test
	public void sendEmotionInvalidBodyTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
				.post(SEND_EMOTION_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("Fa1l"))
		.andExpect(MockMvcResultMatchers.status().isPreconditionFailed());
	}

	@Test
	public void sendEmotionCorrectTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(SEND_EMOTION_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("Happy"))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
}

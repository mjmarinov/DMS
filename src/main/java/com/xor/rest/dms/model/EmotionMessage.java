package com.xor.rest.dms.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@DiscriminatorValue("send_emotion")
@Entity
public class EmotionMessage extends AbstractMessage {

	@Override
	@Pattern(regexp = "^[^0-9]{2,10}$")
	@NotBlank
	public String getPayload() {
		return super.getPayload();
	}
}

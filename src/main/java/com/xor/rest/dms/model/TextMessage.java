package com.xor.rest.dms.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@DiscriminatorValue("send_text")
@Entity
public class TextMessage extends AbstractMessage {

	@Override
	@Size(min = 1, max = 160)
	@NotBlank
	public String getPayload() {
		return super.getPayload();
	}
}

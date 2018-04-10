package com.machine.socket.netty.readTimeout;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Response implements Serializable {

	private String id;
	private String name;
	private String responseMessage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}

package com.saasovation.common.port.adapter.messaging.rabbitmq;

import java.util.Date;

public class MessageParameters {

	private String type;
	private String messageId;
	private Date occurredOn;
	private String routeKey;
	private MessageParameters(String type, String messageId, Date occurredOn,String routeKey) {
		super();
		this.type = type;
		this.messageId = messageId;
		this.occurredOn = occurredOn;
		this.routeKey = routeKey;
	}
	public static MessageParameters durableTextParameters(String type,
			String messageId, Date occurredOn) {
		return new MessageParameters(type, messageId, occurredOn,"*");
	}
	public static MessageParameters durableTextParameters(String type,
														  String messageId, Date occurredOn,String routeKey) {
		return new MessageParameters(type, messageId, occurredOn,routeKey);
	}
	public String getType() {
		return type;
	}
	public String getMessageId() {
		return messageId;
	}
	public Date getOccurredOn() {
		return occurredOn;
	}

	public String getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(String routeKey) {
		this.routeKey = routeKey;
	}
}

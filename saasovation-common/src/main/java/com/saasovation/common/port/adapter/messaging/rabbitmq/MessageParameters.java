package com.saasovation.common.port.adapter.messaging.rabbitmq;

import java.util.Date;

public class MessageParameters {

	private String type;
	private String messageId;
	private Date occurredOn;
	private String routeKey;
	//rabbitmq 消息持久化 2：持久化
	private Integer deliveryMode;
	private MessageParameters(String type, String messageId, Date occurredOn,String routeKey,Integer deliveryMode) {
		super();
		this.type = type;
		this.messageId = messageId;
		this.occurredOn = occurredOn;
		this.routeKey = routeKey;
		this.deliveryMode = deliveryMode;
	}
	public static MessageParameters durableTextParameters(String type,
			String messageId, Date occurredOn) {
		return new MessageParameters(type, messageId, occurredOn,"*",1);
	}
	public static MessageParameters durableTextParameters(String type,
														  String messageId, Date occurredOn,String routeKey) {
		return new MessageParameters(type, messageId, occurredOn,routeKey,1);
	}
	public static MessageParameters durableTextParameters(String type,
														  String messageId, Date occurredOn,String routeKey,Integer deliveryMode) {
		return new MessageParameters(type, messageId, occurredOn,routeKey,deliveryMode);
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
	public Integer getDeliveryMode() {
		return deliveryMode;
	}
}

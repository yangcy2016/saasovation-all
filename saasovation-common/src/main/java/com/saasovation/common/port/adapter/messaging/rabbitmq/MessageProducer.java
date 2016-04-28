package com.saasovation.common.port.adapter.messaging.rabbitmq;

import com.rabbitmq.client.AMQP.BasicProperties;

import java.io.IOException;

public class MessageProducer {

	private Exchange exchange;

	public MessageProducer(Exchange exchange) {
		this.exchange = exchange;
	}

	public void send(String notification, MessageParameters messageParameters) {
		try {
			exchange.call(notification,
					createMessageProperties(messageParameters),messageParameters.getRouteKey());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String notification,String routeKey) {
		try {
			exchange.call(notification,routeKey);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BasicProperties createMessageProperties(MessageParameters parameters) {
		return new BasicProperties.Builder()//
				.contentType("application/json")//
				.contentEncoding("UTF-8")//
				.messageId(parameters.getMessageId())//
				.timestamp(parameters.getOccurredOn())//
				.type(parameters.getType())//
				.build();//
	}

	public void close() {
		this.exchange.close();
	}

}

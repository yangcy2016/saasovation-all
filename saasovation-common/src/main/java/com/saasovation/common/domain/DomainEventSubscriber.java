package com.saasovation.common.domain;

public interface DomainEventSubscriber<T> {

	Class<?> subscribedToEventType();
	void 	handle(T aDomainEvent);
}

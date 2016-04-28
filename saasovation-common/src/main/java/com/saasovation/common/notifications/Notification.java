package com.saasovation.common.notifications;

import com.saasovation.common.domain.DomainEvent;

import java.util.Date;


public class Notification {

	private Date occurredOn;
	private long eventId;
	private String type;
	private String eventName;
	private long notificationId;
	private DomainEvent domainEvent;
	private int version = 001;

	public Notification(String simpleName, long eventId, Date occurredOn,
			DomainEvent domainEvent) {
		this.eventName = simpleName;
		this.eventId = eventId;
		this.occurredOn = occurredOn;
		this.domainEvent = domainEvent;
	}

	public Date occurredOn() {
		return this.occurredOn;
	}

	public long eventId() {
		return this.eventId;
	}

	public String type() {
		return this.type;
	}

	public String eventName() {
		return this.eventName;
	}

	public long notificationId() {
		return this.notificationId;
	}

	public DomainEvent domainEvent() {
		return this.domainEvent;
	}

}

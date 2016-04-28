package com.saasovation.common.eventstore;

import java.util.Date;

public class StoredEvent {

	private String eventBody;
	private long eventId;
	private Date occurredOn;
	private String typeName;

	public StoredEvent() {
	}

	public StoredEvent(String typeName, Date occurredOn, String eventBody) {
		super();
		this.eventBody = eventBody;
		this.occurredOn = occurredOn;
		this.typeName = typeName;
	}

	public String eventBody() {
		return this.eventBody;
	}
	public long eventId(){
		return this.eventId;
	}

	@Override
	public String toString() {
		return "StoredEvent [eventBody=" + eventBody + ", eventId=" + eventId
				+ ", occurredOn=" + occurredOn + ", typeName=" + typeName + "]";
	}
}

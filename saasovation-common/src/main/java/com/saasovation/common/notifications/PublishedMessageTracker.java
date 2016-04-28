package com.saasovation.common.notifications;

public class PublishedMessageTracker {
	private long mostRecentPublishedMessageId;
	private long trackerId;
	private String type;//if mq is rabbit type is exchange name

	public PublishedMessageTracker(String type) {
		super();
		this.type = type;
	}

	public long mostRecentPublishedMessageId() {
		return this.mostRecentPublishedMessageId;
	}

	public long trackerId() {
		return this.trackerId;
	}

	public String type() {
		return this.type;
	}

}

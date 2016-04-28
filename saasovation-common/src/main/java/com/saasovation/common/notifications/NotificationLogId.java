package com.saasovation.common.notifications;

public class NotificationLogId {
	private long low;
	private long high;

	public NotificationLogId() {
		super();
	}

	public NotificationLogId(long low, long high) {
		super();
		this.low = low;
		this.high = high;
	}

	public long low() {
		return this.low;
	}

	public long high() {
		return this.high;
	}

	public String encode() {
		StringBuilder sub = new StringBuilder();
		sub
		.append("Link:<http://iam/notification/")//
		.append(this.low())//
		.append(",")//
		.append(this.high());//
		return sub.toString();
	}

	public static String encode(NotificationLogId aNotificationLogId) {
		return aNotificationLogId.encode();
	}

	public NotificationLogId next(long logNotificationCount) {
		return new NotificationLogId(this.high + 1, this.high
				+ logNotificationCount);
	}

	public NotificationLogId previous(long logNotificationCount) {
		return new NotificationLogId(this.low - logNotificationCount,
				this.low - 1);
	}
}

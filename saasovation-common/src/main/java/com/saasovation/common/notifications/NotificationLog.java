package com.saasovation.common.notifications;

import java.util.List;

public class NotificationLog {
	
	private List<Notification> notifications;
	private boolean archivedIndicator;
	private String self;
	private String next;
	private String previous;

	public NotificationLog(String self, String next, String previous,
			List<Notification> notifications, boolean archivedIndicator) {
		this.self = self;
		this.next = next;
		this.previous = previous;
		this.notifications = notifications;
		this.archivedIndicator = archivedIndicator;
	}

}

package com.saasovation.common.notifications;

import com.saasovation.common.domain.DomainEvent;
import com.saasovation.common.eventstore.EventStore;
import com.saasovation.common.eventstore.StoredEvent;
import com.saasovation.common.port.adapter.messaging.rabbitmq.ConnectionSettings;
import com.saasovation.common.port.adapter.messaging.rabbitmq.Exchange;
import com.saasovation.common.port.adapter.messaging.rabbitmq.MessageParameters;
import com.saasovation.common.port.adapter.messaging.rabbitmq.MessageProducer;
import com.saasovation.common.serializater.ObjectSerializater;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
	private static final long LOG_NOTIFICATION_COUNT = 20;// 每个当前日志所包含的通知数
	private static final String EXCHANGE = "com.sand.agilepm_core";
	// 事务标签
	public NotificationLog currentNotificationLog() {
		return null;
	}

	// 事务标签
	public NotificationLog notificationLog(NotificationLogId aNotificationLogId) {
		return null;
	}

	// 事务标签
	public void publishNotifications() {
		PublishedMessageTracker publishedMessageTracker = this.publishedMessageTracker();
		List<Notification> notifications = this.listUnPublishedNotifications(
				publishedMessageTracker.mostRecentPublishedMessageId());
		MessageProducer messageProducer = this.messageProducer();
		try{
			for(Notification notification:notifications){
				this.publish(notification, messageProducer);
			}
			this.trackeMostRecentPublishedMessage(publishedMessageTracker,notifications);
		}finally{
			messageProducer.close();
		}
	}

	private void trackeMostRecentPublishedMessage(
			PublishedMessageTracker publishedMessageTracker,
			List<Notification> notifications) {
		
		
	}
	protected NotificationLogId calculateCurrentNotificationLogId(
			EventStore anEventStore) {
		long count = anEventStore.countStoredEvents();
		long remainder = count % LOG_NOTIFICATION_COUNT;
		if (remainder == 0) {
			remainder = LOG_NOTIFICATION_COUNT;
		}
		long low = count - remainder + 1;
		long high = low + LOG_NOTIFICATION_COUNT - 1;
		return new NotificationLogId(low, high);
	}

	protected NotificationLog findNotificationLog(
			NotificationLogId aNotificationLogId, EventStore anEventStore) {
		List<StoredEvent> storedEvents = anEventStore.allStoredEventsBetween(//
				aNotificationLogId.low(), //
				aNotificationLogId.high());//
		long count = anEventStore.countStoredEvents();
		boolean archivedIndicator = aNotificationLogId.high() < count;
		NotificationLog notificationLog = new NotificationLog(
				aNotificationLogId.encode(), 
				NotificationLogId.encode(aNotificationLogId.next(LOG_NOTIFICATION_COUNT)), 
				NotificationLogId.encode(aNotificationLogId.previous(LOG_NOTIFICATION_COUNT)),
				this.notificationsFrom(storedEvents),
				archivedIndicator);
		return notificationLog;
	}
	
	protected List<Notification> notificationsFrom(List<StoredEvent> aStoredEvents){
		List<Notification> notifications = new ArrayList<Notification>(aStoredEvents.size());
		for(StoredEvent storedEvent:aStoredEvents){
			DomainEvent domainEvent = EventStore.toDomainEvent(storedEvent);
			Notification notification = new Notification(
					domainEvent.getClass().getSimpleName(),
					storedEvent.eventId(),
					domainEvent.occurredOn(),
					domainEvent);
			
			notifications.add(notification);
		}
		return notifications;
	}
	
	protected List<Notification> listUnPublishedNotifications(long aMostRecentPublishedMessageId){
		EventStore eventStore = EventStore.instance();
		List<StoredEvent> storedEvents = eventStore.allStoredEventsSince(aMostRecentPublishedMessageId);
		return this.notificationsFrom(storedEvents);
	}
	
	private PublishedMessageTracker publishedMessageTracker(){
		PublishedMessageTracker tracker = null; //query form db
		if(tracker ==null){
			tracker = new PublishedMessageTracker(EXCHANGE);
		}
		return tracker;
	}
	
	protected MessageProducer messageProducer(){
		Exchange exchange = Exchange.fanOutInstance(
				ConnectionSettings.instance(),
				EXCHANGE, 
				true);	
		return new MessageProducer(exchange);
	}
	
	private void publish(Notification aNotification,MessageProducer producer){
		MessageParameters messageParameters = MessageParameters.durableTextParameters(
				aNotification.type(),
				Long.toString(aNotification.notificationId()),
				aNotification.occurredOn());
		String notification = NotificationService.objectSerializater()
				.serialize(aNotification.domainEvent());
		
		producer.send(notification,messageParameters);
	}
	
	public static ObjectSerializater objectSerializater(){
		return ObjectSerializater.newInstance();
	}
}

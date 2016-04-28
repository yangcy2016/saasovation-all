package com.saasovation.common.eventstore;

import com.saasovation.common.domain.DomainEvent;
import com.saasovation.common.serializater.ObjectSerializater;

import java.util.List;


public class EventStore {
	
	private static final EventStore instance = new EventStore();
	
	public static EventStore instance(){
		return instance;
	}

	private StoredEvent storedEvent;

	public void append(DomainEvent aDomainEvent) {
		String eventSerialization = EventStore.objectSerializater().serialize(
				aDomainEvent);
		StoredEvent storedEvent = new StoredEvent(//
				aDomainEvent.getClass().getName(), //
				aDomainEvent.occurredOn(), //
				eventSerialization);//
		this.session().save(storedEvent);
		setStoredEvent(storedEvent);
	}

	private Session session() {
		return new Session();
	}

	public void setStoredEvent(StoredEvent storedEvent) {
		this.storedEvent = storedEvent;
	}

	public static ObjectSerializater objectSerializater() {
		return ObjectSerializater.newInstance();
	}

	private class Session {
		public void save(StoredEvent storedEvent) {
			System.out.println("saved:"+storedEvent);
		}
	}

	public long countStoredEvents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<StoredEvent> allStoredEventsBetween(long low, long high) {
		
		return null;
	}

	public static DomainEvent toDomainEvent(StoredEvent storedEvent2) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StoredEvent> allStoredEventsSince(
			long aMostRecentPublishedMessageId) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.saasovation.common.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class DomainEventPublisher {
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal<List> subscribers = 
			new ThreadLocal<List>();
	private static final ThreadLocal<Queue> queue = new ThreadLocal<Queue>(){
		@Override
		protected Queue initialValue() {
			return new LinkedBlockingQueue();
		}
	};
	private static final ThreadLocal<Boolean> publishing = new ThreadLocal<Boolean>(){
		@Override
		protected Boolean initialValue() {
			return Boolean.FALSE;
		}
	};
	
	public static DomainEventPublisher instance(){
		return new DomainEventPublisher();
	}
	
	@SuppressWarnings("unchecked")
	public <T> void publish(final T aDomainEvent){
		if(publishing.get()){
			System.out.println("A publish task is on the hole put event in queue!");
			inQueue(aDomainEvent);
			return;
		}
		try{
			publishing.set(Boolean.TRUE);
			List<DomainEventSubscriber<T>> registeredSubscrubers = subscribers.get();
			if(registeredSubscrubers!=null){
				Class<?> eventType = aDomainEvent.getClass();
				for(DomainEventSubscriber<T> subscriber:registeredSubscrubers){
					Class<?> subscribedTo = subscriber.subscribedToEventType();
					if(subscribedTo==eventType||
					   subscribedTo==DomainEvent.class||
							eventType.getSuperclass()==subscribedTo){
						subscriber.handle(aDomainEvent);
					}
				}
			}
		}finally{
			publishing.set(Boolean.FALSE);
			publishQueuedEvent();
		}
	}

	private void publishQueuedEvent(){
		while (hasElement()){
			System.out.println("process queue event!");
			publish(take());
		}
	}

	private  <T> void inQueue(T event){
		queue.get().add(event);
	}

	private DomainEvent take(){
		return (DomainEvent)queue.get().poll();
	}

	private boolean hasElement(){
		return !queue.get().isEmpty();
	}

	public DomainEventPublisher reset(){
		if(!publishing.get()){
			subscribers.set(null);
			queue.set(null);
		}
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <T> void subscribe(DomainEventSubscriber<T> ... subscriber){
		if(publishing.get()){
			return;
		}
		List<DomainEventSubscriber<T>> registedSubscribers = subscribers.get();
		if(registedSubscribers==null){
			registedSubscribers = new ArrayList<DomainEventSubscriber<T>>();
			subscribers.set(registedSubscribers);
		}
		for(DomainEventSubscriber<T> aSubscriber:subscriber){
			registedSubscribers.add(aSubscriber);
		}
	}
}

package com.saasovation.common.domain;

public class DomainEventIdCreator {
	private static long id = 0;
	public static long getId(){
		return id++;
	}
}

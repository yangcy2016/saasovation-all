package com.saasovation.common.domain;

import java.util.Date;

public interface DomainEvent {
	long id();
	Date occurredOn();
	long version();
}

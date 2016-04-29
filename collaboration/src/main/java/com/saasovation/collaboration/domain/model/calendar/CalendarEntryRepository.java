package com.saasovation.collaboration.domain.model.calendar;

import java.util.Collection;

public interface CalendarEntryRepository {
	
	void add(CalendarEntry aCalendarEntry);
	
	void addAll(Collection<CalendarEntry> aCalendarEntryCollection);
	
	void remove(CalendarEntry aCalendarEntry);
	
	void removeAll(Collection<CalendarEntry> aCalendarEntryCollection);
	
	
}

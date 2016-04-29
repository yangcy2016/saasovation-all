package com.saasovation.collaboration.infrastructure.persistence;

import com.saasovation.collaboration.domain.model.calendar.CalendarEntry;
import com.saasovation.collaboration.domain.model.calendar.CalendarEntryRepository;

import java.util.Collection;


/**
 * 将资源库的实现放在基础设施层，
 * 是使用了依赖倒置的方式，
 * 从逻辑上看基础实施层位于所有层之上
 * 并且向下单向的引用领域层
 * @author Administrator
 *
 */
public class HibernateCalendarEntryRepository implements CalendarEntryRepository {

	public void add(CalendarEntry aCalendarEntry) {
		// TODO Auto-generated method stub
		
	}

	public void addAll(Collection<CalendarEntry> aCalendarEntryCollection) {
		// TODO Auto-generated method stub
		
	}

	public void remove(CalendarEntry aCalendarEntry) {
		// TODO Auto-generated method stub
		
	}

	public void removeAll(Collection<CalendarEntry> aCalendarEntryCollection) {
		// TODO Auto-generated method stub
		
	}

}

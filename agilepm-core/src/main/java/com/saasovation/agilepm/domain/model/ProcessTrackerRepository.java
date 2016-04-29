package com.saasovation.agilepm.domain.model;

import com.saasovation.agilepm.infrastructure.messaging.TimeConstrainedProcessTracker;
import com.saasovation.common.tracker.ProcessId;

import java.util.Collection;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 ионГ 11:30
 * @since : ${VERSION}
 */
public interface ProcessTrackerRepository {
    TimeConstrainedProcessTracker trackerOfProcessId(ProcessId processId);

    void add(TimeConstrainedProcessTracker tracker);

    Collection<TimeConstrainedProcessTracker> allTimedOut();
}

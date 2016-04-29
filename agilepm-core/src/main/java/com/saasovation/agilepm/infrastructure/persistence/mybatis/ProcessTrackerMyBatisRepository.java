package com.saasovation.agilepm.infrastructure.persistence.mybatis;

import com.saasovation.agilepm.domain.model.ProcessTrackerRepository;
import com.saasovation.agilepm.infrastructure.messaging.TimeConstrainedProcessTracker;
import com.saasovation.common.tracker.ProcessId;

import java.util.Collection;
import java.util.Date;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 ÏÂÎç 2:50
 * @since : ${VERSION}
 */
public class ProcessTrackerMyBatisRepository implements ProcessTrackerRepository {
    @Override
    public TimeConstrainedProcessTracker trackerOfProcessId(ProcessId processId) {
        return new TimeConstrainedProcessTracker(
                null,
                processId.getId(),
                "",
                new Date(),
                5*60*1000,
                3,
                "");
    }

    @Override
    public void add(TimeConstrainedProcessTracker tracker) {

    }

    @Override
    public Collection<TimeConstrainedProcessTracker> allTimedOut() {
        return null;
    }
}

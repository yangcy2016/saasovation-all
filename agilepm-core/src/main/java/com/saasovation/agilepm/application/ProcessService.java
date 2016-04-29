package com.saasovation.agilepm.application;

import com.saasovation.agilepm.domain.model.DomainRegistry;
import com.saasovation.agilepm.infrastructure.messaging.TimeConstrainedProcessTracker;

import java.util.Collection;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 下午 12:24
 * @since : ${VERSION}
 */
public class ProcessService
{

    public void checkForTimedOutProcesses(){
        Collection<TimeConstrainedProcessTracker> trackers = DomainRegistry.processTrackerRepository().allTimedOut();
        for(TimeConstrainedProcessTracker tracker:trackers){
            tracker.informProcessTimedOut();
        }
    }
}

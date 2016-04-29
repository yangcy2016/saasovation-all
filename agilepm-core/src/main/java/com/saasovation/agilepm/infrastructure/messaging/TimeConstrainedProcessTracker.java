package com.saasovation.agilepm.infrastructure.messaging;

import com.saasovation.agilepm.domain.model.product.ProductDiscussionRequestTimedOut;
import com.saasovation.common.domain.DomainEventPublisher;
import com.saasovation.common.tracker.ProcessId;

import java.util.Date;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 上午 10:40
 * @since : ${VERSION}
 */
public class TimeConstrainedProcessTracker {
    private String targetId;
    private String processId;
    private String description;
    private Date   createDate;
    private int totalRetriesPermitted;
    private int retryCount;
    private String timeOutEventName;

    public TimeConstrainedProcessTracker(String targetId, String processId, String description,
                                         Date createDate, int totalRetriesPermitted,
                                         int retryCount, String timeOutEventName) {
        this.targetId = targetId;
        this.processId = processId;
        this.description = description;
        this.createDate = createDate;
        this.totalRetriesPermitted = totalRetriesPermitted;
        this.retryCount = retryCount;
        this.timeOutEventName = timeOutEventName;
    }

    //对重试或者超时进行确认,确认之后发布ProcessTimedOut事件
    public void informProcessTimedOut(){

        //Do something
        DomainEventPublisher.instance().publish(
                new ProductDiscussionRequestTimedOut(
                        getTargetId(),
                        ProcessId.existingProcessId(getProcessId()),
                        getTotalRetriesPermitted(),
                        getRetryCount()));
    }

    public void completed() {

    }

    public String getTargetId() {
        return targetId;
    }

    public String getProcessId() {
        return processId;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getTotalRetriesPermitted() {
        return totalRetriesPermitted;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public String getTimeOutEventName() {
        return timeOutEventName;
    }
}

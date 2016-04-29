package com.saasovation.common.tracker;

import com.saasovation.common.domain.AbstractDomainEvent;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 上午 10:30
 * @since : ${VERSION}
 */
public abstract class ProcessTimeOut extends AbstractDomainEvent{
    private String tenantId;
    private ProcessId processId;
    private int totalRetriesPermitted;
    private int retryCount;
    private int allowsReties;

    public ProcessTimeOut(String tenantId, ProcessId processId, int totalRetriesPermitted, int retryCount) {
        this.tenantId = tenantId;
        this.processId = processId;
        this.totalRetriesPermitted = totalRetriesPermitted;
        this.retryCount = retryCount;
    }

    /**判断事件属于完全超时还是重试
     * @return
     */
    public boolean hasFullyTimeOut(){
        return false;
    }

    public int allowsReties(){
        return this.allowsReties;
    }

    public int retryCount(){
        return this.retryCount;
    }

    public int totalRetriesPermitted(){
        return this.totalRetriesPermitted;
    }

    public String getTenantId() {
        return tenantId;
    }

    public ProcessId getProcessId() {
        return processId;
    }

    public int getTotalRetriesPermitted() {
        return totalRetriesPermitted;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public int getAllowsReties() {
        return allowsReties;
    }
}

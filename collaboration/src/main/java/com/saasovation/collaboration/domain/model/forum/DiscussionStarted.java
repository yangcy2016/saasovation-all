package com.saasovation.collaboration.domain.model.forum;

import com.saasovation.common.domain.AbstractDomainEvent;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 ÉÏÎç 9:10
 * @since : ${VERSION}
 */
public class DiscussionStarted extends AbstractDomainEvent{
    private String tenantId;
    private String discussionId;
    private String exclusiveOwner;

    public DiscussionStarted(String tenantId, String discussionId, String exclusiveOwner) {
        this.tenantId = tenantId;
        this.discussionId = discussionId;
        this.exclusiveOwner = exclusiveOwner;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getDiscussionId() {
        return discussionId;
    }

    public String getExclusiveOwner() {
        return exclusiveOwner;
    }
}

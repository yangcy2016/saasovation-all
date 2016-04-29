package com.saasovation.agilepm.domain.model.product;

import com.saasovation.common.tracker.ProcessId;
import com.saasovation.common.tracker.ProcessTimeOut;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 ионГ 10:51
 * @since : ${VERSION}
 */
public class ProductDiscussionRequestTimedOut extends ProcessTimeOut {

    public ProductDiscussionRequestTimedOut(String tenantId, ProcessId processId, int totalRetriesPermitted, int retryCount) {
       super(tenantId,processId,totalRetriesPermitted,retryCount);
    }
}

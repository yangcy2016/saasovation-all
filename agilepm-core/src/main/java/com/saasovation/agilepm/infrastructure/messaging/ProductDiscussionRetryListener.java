package com.saasovation.agilepm.infrastructure.messaging;

import com.saasovation.agilepm.application.product.ProductApplicationService;
import com.saasovation.agilepm.domain.model.product.ProductDiscussionRequestTimedOut;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.common.notifications.Notification;
import com.saasovation.common.port.adapter.messaging.rabbitmq.AutomaticExchangeListener;
import com.saasovation.common.serializater.ObjectSerializer;
import org.springframework.stereotype.Component;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 上午 10:50
 * @since : ${VERSION}
 */
@Component
public class ProductDiscussionRetryListener extends AutomaticExchangeListener{
    @Override
    protected void filteredDispatch(String type, String textMessage) {
        Notification notification = ObjectSerializer.newInstance()
                .deserialize(textMessage,Notification.class);
        ProductDiscussionRequestTimedOut event = (ProductDiscussionRequestTimedOut) notification.domainEvent();
        if(event.hasFullyTimeOut()){
            productApplicationService().timeOutProductDiscussionRequest(
                    new TenantId(event.getTenantId()),
                    event.getProcessId().getId(),
                    event.occurredOn());
        }else {
            productApplicationService().retryProductDiscussionRequest(
                    new TenantId(event.getTenantId()),
                    event.getProcessId().getId());
        }
    }

    private ProductApplicationService productApplicationService(){
        return null;
    }

    @Override
    protected String[] listensToEvents() {
        return new String[]{
                "com.saasovation.agilepm.domain.model.product.ProductDiscussionRequestTimedOut"
        };
    }

    @Override
    public String exchangeName() {
        return Exchangers.AGILEPM_EXCHNAGE_NAME;
    }

    @Override
    public String routeKey() {
        return "*.timeout";
    }
}

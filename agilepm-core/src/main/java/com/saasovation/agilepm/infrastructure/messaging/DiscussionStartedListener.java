package com.saasovation.agilepm.infrastructure.messaging;

import com.saasovation.agilepm.application.product.ProductApplicationService;
import com.saasovation.common.media.NotificationReader;
import com.saasovation.common.port.adapter.messaging.rabbitmq.AutomaticExchangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 上午 9:08
 * @since : ${VERSION}
 */
@Component
public class DiscussionStartedListener extends AutomaticExchangeListener{

    @Autowired
    private ProductApplicationService applicationService;

    @Override
    protected void filteredDispatch(String type, String textMessage) {
        NotificationReader reader = new NotificationReader(textMessage);
        String tenantId = reader.stringValue("tenantId");
        String productId = reader.stringValue("exclusiveOwner");
        String discussionId = reader.stringValue("discussionId");
        productApplicationService().initiateDiscussion(
                tenantId,
                productId,
                discussionId);
    }

    public ProductApplicationService productApplicationService(){
        return applicationService;
    }

    @Override
    protected String[] listensToEvents() {
        return new String[]{
                "com.saasovation.collaboration.domain.model.forum.DiscussionStarted"
        };
    }

    @Override
    public String exchangeName() {
        return Exchangers.COLLABORATION_EXCHANGE_NAME;
    }

    @Override
    public String routeKey() {
        return "*.started";
    }
}

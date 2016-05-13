package com.saasovation.agilepm.infrastructure.messaging;

import com.saasovation.agilepm.application.product.ProductApplicationService;
import com.saasovation.common.container.Container;
import com.saasovation.common.media.NotificationReader;
import com.saasovation.common.port.adapter.messaging.rabbitmq.*;
import com.saasovation.common.serializater.ObjectSerializer;
import com.saasovation.common.serializater.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 上午 9:01
 * @since : ${VERSION}
 */
@Component
public class ProductDiscussionRequestedListener extends AutomaticExchangeListener{

    private static final String COMMAND = "com.saasovation.collaboration.discussion.CreateExclusiveDiscussion";
    private static final Logger logger = LoggerFactory.getLogger(ProductDiscussionRequestedListener.class);
    @Override
    protected void filteredDispatch(String type, String textMessage) {
        NotificationReader reader = new NotificationReader(textMessage);
        if(!reader.booleanValue("requestingDiscussion")){
            return;
        }
        Properties paramters = paramtersFrom(reader);
        String tenantId = reader.stringValue("tenantId.id");
        String productId =  reader.stringValue("productId.id");
        String serialization = ObjectSerializer.newInstance().serialize(paramters.content());
        productApplicationService().startDiscussionInitiation(
                tenantId,
                productId);
        this.messageProducer().send(serialization,
                MessageParameters.durableTextParameters(COMMAND,messageId(),new Date(),"discussion.requested"))
        .close();
    }

    private ProductApplicationService productApplicationService(){
        return  Container.instance().getBean("com.saasovation.agilepm.application.product.ProductApplicationService",
                ProductApplicationService.class);
    }

    private Properties paramtersFrom(NotificationReader reader){
        Properties properties = new Properties();
        String tenantId = reader.stringValue("tenantId.id");
        String exclusiveOwnerId = reader.stringValue("productOwnerId.id");
        String forumTitle = reader.stringValue("productName")+"-"+"talk forum";
        String forumDescription = "this is product forum created for product";
        String discussionSubject = reader.stringValue("description");
        String creatorId = reader.stringValue("productId.id");
        String moderatorId = reader.stringValue("productOwnerId.id");
        properties.add("tenantId",tenantId);
        properties.add("exclusiveOwnerId",exclusiveOwnerId);
        properties.add("forumTitle",forumTitle);
        properties.add("forumDescription",forumDescription);
        properties.add("discussionSubject",discussionSubject);
        properties.add("creatorId",creatorId);
        properties.add("moderatorId",moderatorId);
        return properties;
    }

    private String messageId(){
        return UUID.randomUUID().toString();
    }

    private MessageProducer messageProducer() {
        Exchange exchange = Exchange.topicInstance(
                ConnectionSettings.instance(),
                Exchangers.COLLABORATION_EXCHANGE_NAME,
                true);
        return new MessageProducer(exchange);
    }

    @Override
    protected String[] listensToEvents() {
        return new String[]{
                "com.saasovation.agilepm.domain.model.product.ProductCreated",
                "com.saasovation.agilepm.domain.model.product.ProductDiscussionRequested"
        };
    }

    @Override
    public String exchangeName() {
        return Exchangers.AGILEPM_EXCHNAGE_NAME;
    }

    @Override
    public String routeKey() {
        return "*.created";
    }

}

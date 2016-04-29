package com.saasovation.collaboration.infrastructure.messaging;

import com.saasovation.collaboration.application.ForumService;
import com.saasovation.common.media.NotificationReader;
import com.saasovation.common.port.adapter.messaging.rabbitmq.AutomaticExchangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 ÉÏÎç 9:31
 * @since : ${VERSION}
 */
@Component
public class ExclusiveDiscussionCreationListener extends AutomaticExchangeListener{

    @Autowired
    private ForumService forumService;
    private static final Logger logger = LoggerFactory.getLogger(ExclusiveDiscussionCreationListener.class);

    @Override
    protected void filteredDispatch(String type, String textMessage) {
        NotificationReader reader = new NotificationReader(textMessage);
        logger.info("received:{}",textMessage);
        String tenantId = reader.stringValue("tenantId");
        String exclusiveOwnerId = reader.stringValue("exclusiveOwnerId");
        String forumSubject = reader.stringValue("forumTitle");
        String forumDescription = reader.stringValue("forumDescription");
        String discussionSubject = reader.stringValue("discussionSubject");
        String creatorId = reader.stringValue("creatorId");
        String moderatorId = reader.stringValue("moderatorId");
        this.forumService().startExclusiveForumWithDiscussion(
                tenantId,
                exclusiveOwnerId,
                forumSubject,
                forumDescription,
                discussionSubject,
                creatorId,
                moderatorId);
    logger.info("started a forum {} and discussion {}",forumSubject,discussionSubject);
    }

    @Override
    protected String[] listensToEvents() {
        return new String[]{
                "com.saasovation.collaboration.discussion.CreateExclusiveDiscussion"
        };
    }

    private ForumService forumService(){
        return forumService;
    }

    @Override
    public String exchangeName() {
        return Exchanges.COLLABORATION_EXCHANGE_NAME;
    }

    @Override
    public String routeKey() {
        return "*.requested";
    }
}

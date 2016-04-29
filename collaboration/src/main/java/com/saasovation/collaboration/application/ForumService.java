package com.saasovation.collaboration.application;

import com.saasovation.collaboration.domain.model.DomainRegistry;
import com.saasovation.collaboration.domain.model.Tenant;
import com.saasovation.collaboration.domain.model.collaborator.Author;
import com.saasovation.collaboration.domain.model.forum.*;
import com.saasovation.collaboration.infrastructure.messaging.Exchanges;
import com.saasovation.common.domain.AbstractDomainEventSubscriber;
import com.saasovation.common.domain.DomainEventPublisher;
import com.saasovation.common.port.adapter.messaging.rabbitmq.ConnectionSettings;
import com.saasovation.common.port.adapter.messaging.rabbitmq.Exchange;
import com.saasovation.common.port.adapter.messaging.rabbitmq.MessageParameters;
import com.saasovation.common.port.adapter.messaging.rabbitmq.MessageProducer;
import com.saasovation.common.serializater.ObjectSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 1:56
 * @since : ${VERSION}
 */
@Service(value = "com.saasovation.collabration.application.ForumService")
public class ForumService {
    private static final Logger logger = LoggerFactory.getLogger(ForumService.class);

    @Transactional
    public Discussion startDiscussion(String tenantId,String aForumId,String authorId,String subject){
        Tenant   tenant       = new Tenant(tenantId);
        ForumId  forumId     = new ForumId(aForumId);
        Forum    forum        = this.forum(tenant,forumId);
        if(forum == null){
            throw new IllegalStateException("Forum does not exist");
        }
        Author author = DomainRegistry.collaboratorService().authorFrom(tenant,authorId);
        Discussion newDiscussion = forum.startDiscussion(this.forumNavigationService(),author,subject);
        DomainRegistry.discussionRepository().add(newDiscussion);
        return newDiscussion;
    }

    private ForumNavigationService forumNavigationService() {
        return DomainRegistry.forumNavigationService();
    }

    private Forum forum(Tenant tenant, ForumId forumId) {
        return DomainRegistry.forumRepository().find(tenant,forumId);
    }

    public void startExclusiveForumWithDiscussion(String tenantId, String exclusiveOwnerId, String forumSubject,
                                                  String forumDescription, String discussionSubject,
                                                  String creatorId, String moderatorId) {
        Forum forum = new Forum(tenantId,exclusiveOwnerId);
        DomainRegistry.forumRepository().add(forum);
        DomainEventPublisher.instance().subscribe(new AbstractDomainEventSubscriber<DiscussionStarted>() {
            @Override
            public void handle(DiscussionStarted aDomainEvent) {
                String serialization = ObjectSerializer.newInstance().serialize(aDomainEvent);
                messageProducer().send(serialization,
                        MessageParameters.durableTextParameters(
                                "com.saasovation.collaboration.domain.model.forum.DiscussionStarted",
                                UUID.randomUUID().toString(),
                                new Date(),
                                "discussion.started"
                        ));
            }
        });
        logger.info("subscribe:{}",DomainEventPublisher.instance().subscriberSize());
        forum.startDiscussion(null,null,discussionSubject);
    }

    private MessageProducer messageProducer(){
        Exchange exchange = Exchange.topicInstance(
                ConnectionSettings.instance(),
                Exchanges.COLLABORATION_EXCHANGE_NAME,
                true);
        return new MessageProducer(exchange);
    }
}

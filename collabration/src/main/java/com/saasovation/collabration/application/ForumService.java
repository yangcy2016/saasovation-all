package com.saasovation.collabration.application;

import com.saasovation.collabration.domain.model.DomainRegistry;
import com.saasovation.collabration.domain.model.Tenant;
import com.saasovation.collabration.domain.model.collaborator.Author;
import com.saasovation.collabration.domain.model.forum.Discussion;
import com.saasovation.collabration.domain.model.forum.Forum;
import com.saasovation.collabration.domain.model.forum.ForumId;
import com.saasovation.collabration.domain.model.forum.ForumNavigationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 1:56
 * @since : ${VERSION}
 */
@Service(value = "com.saasovation.collabration.application.ForumService")
public class ForumService {


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
}

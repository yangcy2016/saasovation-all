package com.saasovation.collaboration.infrastructure.persistence;

import com.saasovation.collaboration.domain.model.Tenant;
import com.saasovation.collaboration.domain.model.forum.Forum;
import com.saasovation.collaboration.domain.model.forum.ForumId;
import com.saasovation.collaboration.domain.model.forum.ForumRepository;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 обнГ 4:10
 * @since : ${VERSION}
 */
public class HibernateForumRepostitory implements ForumRepository {
    @Override
    public Forum find(Tenant tenant, ForumId forumId) {
        return null;
    }

    @Override
    public void add(Forum forum) {

    }
}

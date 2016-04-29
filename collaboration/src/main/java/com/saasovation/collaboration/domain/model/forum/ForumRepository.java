package com.saasovation.collaboration.domain.model.forum;

import com.saasovation.collaboration.domain.model.Tenant;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 обнГ 2:08
 * @since : ${VERSION}
 */
public interface ForumRepository {
    Forum find(Tenant tenant,ForumId forumId);

    void add(Forum forum);
}

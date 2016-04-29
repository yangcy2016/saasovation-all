package com.saasovation.collaboration.domain.model.forum;


import com.saasovation.collaboration.domain.model.collaborator.Author;
import com.saasovation.common.domain.DomainEventPublisher;

import java.util.UUID;

public class Forum {
	private String tenantId;
	private String ownerId;

	public Forum(String tenantId, String ownerId) {
		this.tenantId = tenantId;
		this.ownerId = ownerId;
	}

	public Forum() {
	}

	public Discussion startDiscussion(
			ForumNavigationService forumNavigationService, Author author,
			String aSubject) {

		DomainEventPublisher.instance().publish(
				new DiscussionStarted(
						tenantId,
						"discussion-"+ UUID.randomUUID().toString(),
						ownerId)
		);
		return null;
	}

}

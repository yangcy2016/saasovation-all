package com.saasovation.collaboration.domain.model;


import com.saasovation.collaboration.domain.model.collaborator.CollaboratorService;
import com.saasovation.collaboration.domain.model.forum.DiscussionRepository;
import com.saasovation.collaboration.domain.model.forum.ForumNavigationService;
import com.saasovation.collaboration.domain.model.forum.ForumRepository;
import com.saasovation.collaboration.infrastructure.persistence.HibernateForumRepostitory;
import com.saasovation.collaboration.infrastructure.services.TranslatingCollaboratorService;

public class DomainRegistry {

	public static CollaboratorService collaboratorService() {
		
		return new TranslatingCollaboratorService();
	}
	
	public static ForumNavigationService forumNavigationService() {
		return null;
	}
	
	public static DiscussionRepository discussionRepository(){
		return null;
	}

	public static ForumRepository forumRepository(){
		return new HibernateForumRepostitory();
	}

}

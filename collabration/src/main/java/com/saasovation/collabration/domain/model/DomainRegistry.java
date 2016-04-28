package com.saasovation.collabration.domain.model;


import com.saasovation.collabration.domain.model.collaborator.CollaboratorService;
import com.saasovation.collabration.domain.model.forum.DiscussionRepository;
import com.saasovation.collabration.domain.model.forum.ForumNavigationService;
import com.saasovation.collabration.domain.model.forum.ForumRepository;
import com.saasovation.collabration.infrastructure.services.TranslatingCollaboratorService;

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
		return null;
	}

}

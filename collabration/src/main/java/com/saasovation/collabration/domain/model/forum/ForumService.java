package com.saasovation.collabration.domain.model.forum;

import com.saasovation.collabration.domain.model.DomainRegistry;
import com.saasovation.collabration.domain.model.Tenant;
import com.saasovation.collabration.domain.model.collaborator.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service(value="com.sand.collabration.domain.model.forum.ForumService")
public class ForumService {
	
	@Transactional
	public Discussion startDiscussion(
					String aTenantId,
					String aForumId,
					String anAuthorId,
					String aSubject){
		Tenant tenant = new Tenant(aTenantId);
		Forum forum = this.froum(tenant,aForumId);
		if(forum==null){
			//throw exception
		}
		Author author = DomainRegistry
				.collaboratorService()
				.authorFrom(tenant, anAuthorId);
		Discussion newDiscussion = forum.startDiscussion(
							DomainRegistry.forumNavigationService(),
							author,
							aSubject);
		
		DomainRegistry.discussionRepository().add(newDiscussion);
		return newDiscussion;
	}

	private Forum froum(Tenant tenant, String aForumId) {
		// TODO Auto-generated method stub
		return null;
	}
}

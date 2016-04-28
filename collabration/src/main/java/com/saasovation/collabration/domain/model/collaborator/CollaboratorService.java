package com.saasovation.collabration.domain.model.collaborator;


import com.saasovation.collabration.domain.model.Tenant;

public interface CollaboratorService {
	Author authorFrom(Tenant aTenant, String anIdentity);
	Creator creatorFrom(Tenant aTenant, String anIdentity);
	Moderator moderatorFrom(Tenant aTenant, String anIdentity);
	Owner ownerFrom(Tenant aTenant, String anIdentity);
	Participant participantFrom(Tenant aTenant, String anIdentity);
	
}

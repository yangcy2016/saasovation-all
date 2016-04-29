package com.saasovation.collaboration.infrastructure.services;


import com.saasovation.collaboration.domain.model.Tenant;
import com.saasovation.collaboration.domain.model.collaborator.*;

public class TranslatingCollaboratorService implements CollaboratorService {

	public Author authorFrom(Tenant aTenant, String anIdentity) {

		return this.userInRoleAdapter()//
				.toCollaborator(//
						aTenant, //
						anIdentity,//
						"Author",//
						Author.class);//
	}

	private UserInRoleAdapter userInRoleAdapter() {
		return UserInRoleAdapter.newInstance();
	}

	public Creator creatorFrom(Tenant aTenant, String anIdentity) {

		return this.userInRoleAdapter()//
				.toCollaborator(//
						aTenant,//
						anIdentity,//
						"Creator",//
						Creator.class);//
	}

	public Moderator moderatorFrom(Tenant aTenant, String anIdentity) {

		return this.userInRoleAdapter()//
				.toCollaborator(//
						aTenant,//
						anIdentity,//
						"Moderator", //
						Moderator.class);//
	}

	public Owner ownerFrom(Tenant aTenant, String anIdentity) {

		return this.userInRoleAdapter()//
				.toCollaborator(//
						aTenant, //
						anIdentity,//
						"Owner", //
						Owner.class);//
	}

	public Participant participantFrom(Tenant aTenant, String anIdentity) {

		return this.userInRoleAdapter()//
				.toCollaborator(//
						aTenant,//
						anIdentity,//
						"Participant",//
						Participant.class);//
	}
}

package com.saasovation.collabration.infrastructure.services;

import com.saasovation.collabration.domain.model.collaborator.Collaborator;
import com.saasovation.common.media.RepresentationReader;

import java.lang.reflect.Constructor;


public class CollaboratorTranslator {

	
	public <T extends Collaborator> T toCollaboratorFromRepresentation(
			String userInRoleRepresentation,Class<T> collaboratorClass) throws Exception{
		
		RepresentationReader reader = new RepresentationReader(userInRoleRepresentation);
		String userName = reader.getAsJsonObject("data").get("username").getAsString();
		return newCollabrator(userName, collaboratorClass);
	}
	
	private <T extends Collaborator> T newCollabrator(String userName,Class<T> collaboratorClass) throws Exception{
		Constructor<T> ctor = collaboratorClass.getConstructor(String.class);
		T collaborator = ctor.newInstance(userName);
		return collaborator;
	}
	
}

package com.saasovation.collabration.infrastructure.services;


import com.saasovation.collabration.domain.model.Tenant;
import com.saasovation.collabration.domain.model.collaborator.Collaborator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class UserInRoleAdapter {
	
	private static final UserInRoleAdapter instance = new UserInRoleAdapter();
	
	
	public static UserInRoleAdapter newInstance(){
		return instance;
	}
	private UserInRoleAdapter() {
	}

	public <T extends Collaborator> T toCollaborator(
								Tenant aTenant,
								String anIdentity,
								String aRoleName,
								Class<T> aCollaboratorClass){
		T collaborator = null;
		Response resp = null;
		try{
			
			WebTarget target = buildWebTarget(aTenant, anIdentity, aRoleName);
			resp = target.request().get();
			
			if(resp.getStatus()==200){
				String value = resp.readEntity(String.class);
				collaborator = new CollaboratorTranslator().toCollaboratorFromRepresentation(
						value,
						aCollaboratorClass);
			}
			else if(resp.getStatus()==204){
				throw new IllegalStateException("there is problem requesting the user:"+anIdentity
						+" inRole:"+aRoleName+" with resultStatus:"+resp.getStatus());
			}
			
		}catch(Throwable e){
			e.printStackTrace();
			throw new IllegalStateException("Failed because:"+e.getCause());
		}finally{
			if(resp !=null){
				resp.close();
			}
		}
		
		
		return collaborator;
	}
	
	private WebTarget buildWebTarget(Tenant aTenant, String anIdentity,
			String aRoleName) {
		Client client = ClientBuilder.newBuilder().build();
		String tenantId = aTenant.getTenantId();
		StringBuilder sub = new StringBuilder();
		sub.append("http://localhost:8002/")
		.append("tenants/")
		.append(tenantId+"/")
		.append("users/")
		.append(anIdentity+"/")
		.append("inRole/")
		.append(aRoleName);
		WebTarget target = client.target(sub.toString());
		return target;
	}
			
}

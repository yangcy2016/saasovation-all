package com.saasovation.identityaccess.domain.model.identity;

public interface AuthenticationService {
	UserDescriptor authenticate(TenantId aTenantId, String aUsername, String aPassword);
}

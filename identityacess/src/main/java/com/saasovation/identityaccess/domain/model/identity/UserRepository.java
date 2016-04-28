package com.saasovation.identityaccess.domain.model.identity;

public interface UserRepository {

	User userFromAuthenticCredentials(TenantId aTenantId, String aUsername,
									  String encryptedPassword);

	User userWithUserName(TenantId aTenantId, String userName);

}

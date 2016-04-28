package com.saasovation.identityaccess.infrastructure.persistence.hibernate;

import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.User;
import com.saasovation.identityaccess.domain.model.identity.UserRepository;
import com.saasovation.identityaccess.domain.model.user.ContactInformation;
import com.saasovation.identityaccess.domain.model.user.Name;
import com.saasovation.identityaccess.domain.model.user.Person;
import org.springframework.stereotype.Repository;

@Repository(value="com.sand.identityacess.infrastructure.persistence.hibernate.UserHibernateRepository")
public class UserHibernateRepository implements UserRepository {

	public User userFromAuthenticCredentials(TenantId aTenantId,
			String aUsername, String encryptedPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	public User userWithUserName(TenantId aTenantId, String userName) {
		Name name = new Name("ronger","huang");
		ContactInformation contact = new ContactInformation(
				"ronnger.huang@qq.com",
				"123456789",
				"shanghai city");
		Person person = new Person(name,contact);
		return new User(userName,"abc123",aTenantId,person);
	}

}

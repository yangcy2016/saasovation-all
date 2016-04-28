package com.saasovation.identityaccess.domain.model;


import com.saasovation.common.container.Container;
import com.saasovation.identityaccess.domain.model.identity.EncryptionService;
import com.saasovation.identityaccess.domain.model.identity.GroupMemberService;
import com.saasovation.identityaccess.domain.model.identity.TenantRepository;
import com.saasovation.identityaccess.domain.model.identity.UserRepository;
import com.saasovation.identityaccess.domain.model.role.RoleRepository;

public class DomainRegistry {
	
	public static TenantRepository tenantRepository() {
		return Container.instance()
				.getBean("com.sand.identityacess.infrastructure.persistence.hibernate.TenantHibernateRepository",
						TenantRepository.class);
	}

	public static EncryptionService encryptionService() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static UserRepository userRepository(){
		return Container.instance()
				.getBean("com.sand.identityacess.infrastructure.persistence.hibernate.UserHibernateRepository",
						UserRepository.class);
	}

	public static RoleRepository roleRepository() {
	
		return Container.instance()
				.getBean("com.sand.identityacess.infrastructure.persistence.hibernate.RoleHibernateRepository",
				RoleRepository.class);
	}

	public static GroupMemberService groupMemberService() {
		
		return new GroupMemberService();
	}

}

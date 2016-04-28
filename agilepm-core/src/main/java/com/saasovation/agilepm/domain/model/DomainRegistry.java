package com.saasovation.agilepm.domain.model;


import com.saasovation.agilepm.domain.model.product.BusinessPriorityCalculator;
import com.saasovation.agilepm.domain.model.product.ProductRepository;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemRepository;
import com.saasovation.agilepm.domain.model.product.release.ReleaseRepository;
import com.saasovation.agilepm.domain.model.product.sprint.SprintRepository;
import com.saasovation.agilepm.domain.model.team.ProductOwnerRepository;
import com.saasovation.agilepm.domain.model.team.TeamOwnerRepository;
import com.saasovation.agilepm.domain.model.team.TeamRepository;
import com.saasovation.agilepm.domain.model.team.TeamService;
import com.saasovation.agilepm.domain.model.tenant.TenantRepository;
import com.saasovation.agilepm.infrastructure.persistence.mybatis.BacklogItemMybatisRepository;
import com.saasovation.agilepm.infrastructure.persistence.mybatis.ProductMybatisRepository;
import com.saasovation.agilepm.infrastructure.persistence.mybatis.SprintMybatisRepository;
import com.saasovation.agilepm.infrastructure.persistence.mybatis.TenantMybatisRepository;

public class DomainRegistry {

	public static BacklogItemRepository backlogItemRepository() {
		return new BacklogItemMybatisRepository();
	}

	public static TenantRepository tenantRepository() {
		// TODO Auto-generated method stub
		return new TenantMybatisRepository();
	}

	public static BusinessPriorityCalculator businessPriorityCalculator() {
		return null;
	}

	public static SprintRepository sprintRepository() {
		return new SprintMybatisRepository();
	}
	
	public static ProductRepository productRepository(){
		return new ProductMybatisRepository();
	}

	public static ReleaseRepository releaseRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	public static TeamRepository teamRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	public static TeamService teamService(){
		return new TeamService();
	}

	public static ProductOwnerRepository productOwnerRepository(){
		return null;
	}

	public static TeamOwnerRepository teamOwnerRepository(){
		return null;
	}
}

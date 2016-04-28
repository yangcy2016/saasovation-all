package com.saasovation.agilepm.domain.model.product.backlogitem;


import com.saasovation.common.domain.ConcurrencySafeEntity;
import com.saasovation.common.domain.DomainEventPublisher;
import com.saasovation.agilepm.domain.model.product.BacklogItemCommitted;
import com.saasovation.agilepm.domain.model.product.BusinessPriority;
import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.sprint.Sprint;
import com.saasovation.agilepm.domain.model.product.sprint.SprintId;
import com.saasovation.agilepm.domain.model.product.task.TaskId;
import com.saasovation.agilepm.domain.model.team.Team;
import com.saasovation.agilepm.domain.model.team.TeamId;
import com.saasovation.agilepm.domain.model.team.TeamMemberId;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

public class BacklogItem extends ConcurrencySafeEntity {

	private BacklogItemId backlogItemId;
	private SprintId sprintId;
	private TenantId tenantId;
	//使用聚合的唯一性标示对聚合进行引用
	private ProductId productId;
	
	private BacklogItemStatus status;
	
	private String story;
	
	private StoryPoints storyPoints;
	private String summary;
	private BacklogItemStatusType type;
	
	private TeamId teamId;

	
	
	public BacklogItem(BacklogItemId backlogItemId, SprintId sprintId,
			TenantId tenantId) {
		super();
		this.backlogItemId = backlogItemId;
		this.sprintId = sprintId;
		this.tenantId = tenantId;
	}

	public BusinessPriority businessPriority() {
		return null;
	}
	
	//在边界之外使用最终一致性
	public void commitTo(Sprint aSprint) {
		// ....

		// publish domain event
		DomainEventPublisher.instance().publish(
				new BacklogItemCommitted(
						this.backlogItemId,
						this.sprintId,
						this.tenantId));
		// ....
	}
	
	public TeamId teamId() {
		return this.teamId;
	}

	public void assignTeamMemberToTask(TeamMemberId teamMemberId, Team ofTeam,
			TaskId taskId) {
		// TODO Auto-generated method stub
		
	}

	public TenantId tenantId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

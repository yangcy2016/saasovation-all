package com.saasovation.agilepm.application.product;


import com.saasovation.agilepm.domain.model.DomainRegistry;
import com.saasovation.agilepm.domain.model.product.Product;
import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItem;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemType;
import com.saasovation.agilepm.domain.model.product.backlogitem.StoryPoints;
import com.saasovation.agilepm.domain.model.product.task.TaskId;
import com.saasovation.agilepm.domain.model.team.Team;
import com.saasovation.agilepm.domain.model.team.TeamMemberId;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

public class ProductBacklogItemService {

	public void planProductBacklogItem(String aTenantId, String aProductId,
			String aSummary, String aCategory, String aBacklogItemType,
			String aStoryPoints) {
		Product product = DomainRegistry.productRepository().productOfId(
				new TenantId(aTenantId), new ProductId(aProductId));
		BacklogItem backlogItem = product.planBacklogItem(aSummary, aCategory,
				BacklogItemType.valueOf(aBacklogItemType),
				StoryPoints.valueOf(aStoryPoints));
		DomainRegistry.backlogItemRepository().add(backlogItem);
	}

	public void assignTeamMemberToTask(String aTenantId, String aBacklogItemId,
			String aTaskId, String aTeamMemberId) {
		BacklogItem backlogItem = DomainRegistry.backlogItemRepository()
				.backlogItemOfId(new TenantId(aTenantId),
						new BacklogItemId(aBacklogItemId));
		Team ofTeam = DomainRegistry.teamRepository().teamOfId(
				backlogItem.tenantId(), backlogItem.teamId());
		backlogItem.assignTeamMemberToTask(new TeamMemberId(aTeamMemberId),
				ofTeam, new TaskId(aTaskId));
		DomainRegistry.backlogItemRepository().update(backlogItem);
	}
}

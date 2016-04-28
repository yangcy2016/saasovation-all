package com.saasovation.agilepm.domain.model.product.task;

import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;

import java.util.Set;

public class Task {
	private TaskId taskId;
	private String description;
	private int hoursRemaining;
	private String name;
	private String volunteer;

	private Set<EstimationLogEntity> estimationLogEntitySet;

	private ProductId productId;
	private BacklogItemId backlogItemId;
	
	
	public void estimateHoursRemaining(){
		
	}

}

package com.saasovation.agilepm.domain.model.product.sprint;

public interface SprintRepository {

	Sprint sprintOfId(SprintId aSprintId);

	void add(Sprint sprint);

}

package com.saasovation.agilepm.domain.model.team;

import java.util.Date;

public class TeamOwner extends TeamMember{

    public TeamOwner(String tenantId, String userName, String firstName, String lastName, String emailAddress, Date occurredOn) {
        super(tenantId, userName, firstName, lastName, emailAddress, occurredOn);
    }
}

package com.saasovation.agilepm.domain.model.team;

import java.util.Date;

/**
 * aggregate root
 * @author Administrator
 *
 */
public class ProductOwner extends Member{
    public ProductOwner(String tenantId, String userName, String firstName, String lastName, String emailAddress, Date occurredOn) {
        super(tenantId, userName, firstName, lastName, emailAddress, occurredOn);
    }
}

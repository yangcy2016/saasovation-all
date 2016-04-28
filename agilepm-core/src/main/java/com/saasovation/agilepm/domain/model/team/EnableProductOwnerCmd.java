package com.saasovation.agilepm.domain.model.team;

import java.util.Date;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 ÏÂÎç 3:27
 * @since : ${VERSION}
 */
public class EnableProductOwnerCmd {
    private String tenantId;
    private String userName;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Date   occurredOn;

    public EnableProductOwnerCmd(String tenantId, String userName, String firstName, String lastName, String emailAddress, Date occurredOn) {
        this.tenantId = tenantId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.occurredOn = occurredOn;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getOccurredOn() {
        return occurredOn;
    }

    public void setOccurredOn(Date occurredOn) {
        this.occurredOn = occurredOn;
    }
}

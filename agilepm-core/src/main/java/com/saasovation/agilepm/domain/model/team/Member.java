package com.saasovation.agilepm.domain.model.team;

import com.saasovation.common.domain.Entity;

import java.util.Date;

public abstract class Member extends Entity{
    private String tenantId;
    private String userName;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Date   occurredOn;

    private boolean enabled;

    private MemberChangeTracker changeTracker;

    public Member(String tenantId, String userName, String firstName, String lastName, String emailAddress, Date occurredOn) {
        this.tenantId = tenantId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.occurredOn = occurredOn;
    }

    public void enable(Date occurredOn){
        if(changeTracker.canToggleEnabling(occurredOn)){
            this.setEnabled(true);
            this.setChangeTracker(changeTracker.enablingOn(occurredOn));
        }
    }

    public void disable(Date occurredOn){
        if(changeTracker.canToggleEnabling(occurredOn)){
            this.setEnabled(false);
            this.setChangeTracker(changeTracker.enablingOn(occurredOn));
        }
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

    public void setChangeTracker(MemberChangeTracker changeTracker) {
        this.changeTracker = changeTracker;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public MemberChangeTracker getChangeTracker() {
        return changeTracker;
    }
}

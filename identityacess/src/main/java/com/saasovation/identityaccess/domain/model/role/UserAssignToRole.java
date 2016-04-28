package com.saasovation.identityaccess.domain.model.role;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saasovation.common.domain.AbstractDomainEvent;
import com.saasovation.identityaccess.domain.model.identity.TenantId;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 ÏÂÎç 2:30
 * @since : ${VERSION}
 */
public class UserAssignToRole extends AbstractDomainEvent{
    @Expose
    @SerializedName("tenantId")
    private TenantId tenantId;

    @Expose
    @SerializedName("roleName")
    private String   roleName;

    @Expose
    @SerializedName("userName")
    private String   userName;

    @Expose
    @SerializedName("firstName")
    private String   firstName;

    @Expose
    @SerializedName("lastName")
    private String   lastName;

    @Expose
    @SerializedName("emailAddress")
    private String   emailAddress;

    public UserAssignToRole(TenantId tenantId, String roleName, String userName, String firstName, String lastName, String emailAddress) {
        this.tenantId = tenantId;
        this.roleName = roleName;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}

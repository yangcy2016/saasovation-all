package com.saasovation.agilepm.infrastructure.messaging;

import com.saasovation.agilepm.application.team.TeamService;
import com.saasovation.agilepm.domain.model.DomainRegistry;
import com.saasovation.agilepm.domain.model.team.EnableProductOwnerCmd;
import com.saasovation.agilepm.domain.model.team.EnableTeamMemberCmd;
import com.saasovation.common.media.NotificationReader;
import com.saasovation.common.port.adapter.messaging.rabbitmq.ConnectionSettings;
import com.saasovation.common.port.adapter.messaging.rabbitmq.Exchange;
import com.saasovation.common.port.adapter.messaging.rabbitmq.ExchangeListener;

import java.util.Date;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 3:03
 * @since : ${VERSION}
 */
public class TeamMemberEnablerListener extends ExchangeListener {

    public TeamMemberEnablerListener() {
        super();
    }

    public TeamService teamService(){
        return DomainRegistry.teamService();
    }

    @Override
    protected void filteredDispatch(String type, String textMessage) {
        NotificationReader reader = new NotificationReader(textMessage);
        String roleName = reader.stringValue("roleName");
        if(!roleName.equals("ScrumProductOwner")&&
           !roleName.equals("ScrumTeamOwner")){
            return;
        }
        String emailAddress = reader.stringValue("emailAddress");
        String firstName    = reader.stringValue("firstName");
        String lastName     = reader.stringValue("lastName");
        String tenantId     = reader.stringValue("tenantId.id");
        String userName     = reader.stringValue("userName");
        Date   occurredOn   = new Date(reader.stringValue("occurredOn"));
        EnableProductOwnerCmd cmd =new EnableProductOwnerCmd(
                tenantId,
                userName,
                firstName,
                lastName,
                emailAddress,
                occurredOn
        );
        if("ScrumProductOwner".equals(roleName)){
            System.out.println(cmd);
            //this.teamService().enableProductOwner(cmd);
        }else {
            this.teamService().enableTeamMemberOwner(
                    new EnableTeamMemberCmd(
                            tenantId,
                            userName,
                            firstName,
                            lastName,
                            emailAddress,
                            occurredOn
                    )
            );
        }
    }

    @Override
    protected String[] listensToEvents() {
        return new String[]{
                "com.saasovation.identityacess.domain.model.role.UserAssignToRole"
        };
    }

    public static void main(String[] args) {
        TeamMemberEnablerListener listener = new TeamMemberEnablerListener();
        Exchange exchange = Exchange.directInstance(
                ConnectionSettings.instance(),
                Exchangers.IDENTITY_ACCESS_EXCHANGE_NAME,
                true);
        listener.attachToQueue(exchange,"userAssignInRole","*");
        listener.registerConsumer();
    }
}

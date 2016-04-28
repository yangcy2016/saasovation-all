package com.saasovation.agilepm.domain.model.team;

import com.saasovation.agilepm.domain.model.DomainRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 ÏÂÎç 3:09
 * @since : ${VERSION}
 */
@Service(value = "com.saasovation.agilepm.domain.model.team.TeamService")
public class TeamService {
    @Transactional
    public void enableProductOwner(EnableProductOwnerCmd cmd) {
        ProductOwner owner = DomainRegistry.productOwnerRepository().productOwnerOfIdentity(
                cmd.getTenantId(),
                cmd.getUserName());
        if(owner!=null){
            owner.enable(cmd.getOccurredOn());
        }else {
            owner = new ProductOwner(
                    cmd.getTenantId(),
                    cmd.getUserName(),
                    cmd.getFirstName(),
                    cmd.getLastName(),
                    cmd.getEmailAddress(),
                    cmd.getOccurredOn()
            );
            DomainRegistry.productOwnerRepository().add(owner);
        }

    }

    @Transactional
    public void enableTeamMemberOwner(EnableTeamMemberCmd cmd) {
        TeamOwner owner = DomainRegistry.teamOwnerRepository().teamOwnerOfIdentity(
            cmd.getTenantId(),
            cmd.getUserName());
        if(owner!=null){
            owner.enable(cmd.getOccurredOn());
        }else {
            owner = new TeamOwner(
                    cmd.getTenantId(),
                    cmd.getUserName(),
                    cmd.getFirstName(),
                    cmd.getLastName(),
                    cmd.getEmailAddress(),
                    cmd.getOccurredOn()
            );
            DomainRegistry.teamOwnerRepository().add(owner);
        }
    }


}

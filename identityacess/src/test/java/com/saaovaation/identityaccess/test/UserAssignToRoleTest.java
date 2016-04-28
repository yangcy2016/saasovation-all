package com.saaovaation.identityaccess.test;

import com.saasovation.common.container.Container;
import com.saasovation.identityaccess.IdentityacessApplication;
import com.saasovation.identityaccess.application.AccessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author : huanghy
 * @create : 2016/4/28 0028 上午 10:46
 * @since : ${VERSION}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IdentityacessApplication.class)
@WebAppConfiguration
public class UserAssignToRoleTest {

    @Autowired
    WebApplicationContext context;


    @Test
    public void test(){
        System.out.println("------>"+context);
        Container.instance().init(context);
        String tenantId = "tenant123";
        String roleName = "ScrumProductOwner";
        String userName = "huang rong er";
        AccessService service = new AccessService();
        service.assignUserToRole(tenantId,userName,roleName);
    }
}

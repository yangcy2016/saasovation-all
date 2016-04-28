package com.saasovation.identityaccess;

import com.saasovation.common.container.Container;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 下午 1:43
 * @since : ${VERSION}
 */
@SpringBootApplication
public class IdentityacessApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(IdentityacessApplication.class);
        ApplicationContext context = application.run(args);
        Container.instance().init(context);
    }

}

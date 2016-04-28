package com.saasovation.agilepm;

import com.saasovation.common.container.Container;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author : huanghy
 * @create : 2016/4/22 0022 下午 1:53
 * @since : ${VERSION}
 */
@SpringBootApplication
public class AgilepmApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AgilepmApplication.class);
        ApplicationContext context = app.run(args);
        Container.instance().init(context);
    }

}

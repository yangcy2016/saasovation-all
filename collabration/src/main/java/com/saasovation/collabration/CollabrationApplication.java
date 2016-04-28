package com.saasovation.collabration;


import com.saasovation.common.container.Container;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 上午 11:28
 * @since : ${VERSION}
 */
@SpringBootApplication
public class CollabrationApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CollabrationApplication.class);
        ApplicationContext context = application.run(args);
        Container.instance().init(context);
    }

}

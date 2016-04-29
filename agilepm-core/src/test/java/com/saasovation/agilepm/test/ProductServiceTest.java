package com.saasovation.agilepm.test;

import com.saasovation.agilepm.AgilepmApplication;
import com.saasovation.agilepm.application.product.ProductApplicationService;
import com.saasovation.common.container.Container;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 下午 1:52
 * @since : ${VERSION}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AgilepmApplication.class)
@WebAppConfiguration
public class ProductServiceTest {
    @Autowired
    private WebApplicationContext context;

    @Test
    public void testProductCreated(){
        Container container = Container.instance().init(context);
        ProductApplicationService service = container.getBean(
                "com.saasovation.agilepm.application.product.ProductApplicationService",
                ProductApplicationService.class);
        String productId = service.newProductWithDiscussion(
                "tenant123",
                "unit test product",
                "zhansan123",
                "product is for a test product");
        System.out.println("------>"+productId);
    }
}

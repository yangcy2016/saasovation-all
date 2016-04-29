package com.saasovation.agilepm.resources;

import com.saasovation.agilepm.application.product.ProductApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 下午 3:00
 * @since : ${VERSION}
 */
@Controller
@RequestMapping(path = "/product")
public class ProductResource {

    @Autowired
    private ProductApplicationService service;

    @RequestMapping(path="/new/{tenantId}/{productOwnerId}/{productName}/of/{desc}")
    @ResponseBody
    public Map newProduct(
            @PathVariable("tenantId")String tenantId,
            @PathVariable("productOwnerId")String productOwnerId,
            @PathVariable("productName")String productName,
            @PathVariable("desc")String desc){
        String productId = service.newProductWithDiscussion(
                tenantId,
                productName,
                productOwnerId,
                desc);
        Map<String,Object> resp = new HashMap<>();
        resp.put("productId",productId);
        resp.put("respCode",000000);
        resp.put("respMsg","success");
        resp.put("respDate",new Date());
        return resp;
    }
}

package com.saasovation.agilepm.domain.model.team;

/**
 * @author : huanghy
 * @create : 2016/4/27 0027 обнГ 3:40
 * @since : ${VERSION}
 */
public interface ProductOwnerRepository {
    ProductOwner productOwnerOfIdentity(String tenantId,String userName);

    void add(ProductOwner productOwner);
}

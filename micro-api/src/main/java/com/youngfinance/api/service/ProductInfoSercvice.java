package com.youngfinance.api.service;

import com.youngfinance.api.model.ProductInfo;
import com.youngfinance.api.pojo.BaseInfo;
import com.youngfinance.api.pojo.MultiProduct;

import java.util.List;

public interface ProductInfoSercvice {

    // 根据产品类型，查询产品，支持分页
    List<ProductInfo> queryByTypeLimit(Integer ptype, Integer pageNo, Integer pageSize);

    // 某个产品类型的记录总数
    Integer queryRecordNumsByType(Integer pType);

    // 首页的多个产品数据
    MultiProduct queryIndexPageProducts();

    // 根据产品id，查询产品信息
    ProductInfo queryById(Integer id);


}

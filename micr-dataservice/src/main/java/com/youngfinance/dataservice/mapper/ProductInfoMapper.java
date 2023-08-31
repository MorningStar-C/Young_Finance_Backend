package com.youngfinance.dataservice.mapper;

import com.youngfinance.api.model.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductInfoMapper {

    // 利率平均值
    BigDecimal selectAvgRate();

    // 按产品类型分页查询
    List<ProductInfo> selectByTypeLimit(@Param("ptype") Integer ptype,
                                        @Param("offset") Integer offset,
                                        @Param("rows") Integer rows);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);

    int selectCountByType(@Param("ptype") Integer pType);
}
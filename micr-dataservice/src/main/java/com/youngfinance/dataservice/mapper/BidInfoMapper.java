package com.youngfinance.dataservice.mapper;

import com.youngfinance.api.model.BidInfo;
import com.youngfinance.api.pojo.BidInfoProduct;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BidInfoMapper {

    // 累计成交金额
    BigDecimal selectSumBidMoney();

    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);

    List<BidInfoProduct> selectByProductId(@Param("productId") Integer productId, @Param("offset") Integer offset, @Param("rows") Integer rows);

}
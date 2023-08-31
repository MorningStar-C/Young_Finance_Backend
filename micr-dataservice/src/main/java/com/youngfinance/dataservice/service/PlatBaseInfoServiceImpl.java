package com.youngfinance.dataservice.service;

import com.youngfinance.api.pojo.BaseInfo;
import com.youngfinance.api.service.PlatBaseInfoService;
import com.youngfinance.dataservice.mapper.BidInfoMapper;
import com.youngfinance.dataservice.mapper.ProductInfoMapper;
import com.youngfinance.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;

@DubboService(interfaceClass = PlatBaseInfoService.class, version = "1.0")
public class PlatBaseInfoServiceImpl implements PlatBaseInfoService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductInfoMapper productInfoMapper;

    @Resource
    private BidInfoMapper bidInfoMapper;

    /**
     * 平台基本信息
     * 获取注册人数、收益率平均值、累计成交金额
     * @return
     */
    @Override
    public BaseInfo queryPlatBaseInfo() {
        // 注册用户数
        int userCounts = userMapper.selectUserCounts();

        // 收益平均值
        BigDecimal avgRate = productInfoMapper.selectAvgRate();

        // 累计成交金额
        BigDecimal bidMoney = bidInfoMapper.selectSumBidMoney();

        BaseInfo baseInfo = new BaseInfo(avgRate, bidMoney, userCounts);

        return baseInfo;
    }
}

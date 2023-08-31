package com.youngfinance.api.service;

import com.youngfinance.api.pojo.BaseInfo;

/**
 * 计算利率、注册人数、累计成交金额
 */
public interface PlatBaseInfoService {

    BaseInfo queryPlatBaseInfo();
}

package com.youngfinance.front.controller;

import com.youngfinance.api.service.PlatBaseInfoService;
import com.youngfinance.api.service.ProductInfoSercvice;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;

public class BaseController {

    @Resource
    protected StringRedisTemplate stringRedisTemplate;
    // 声明公共方法，属性等
    @DubboReference(interfaceClass = PlatBaseInfoService.class, version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;

    @DubboReference(interfaceClass = ProductInfoSercvice.class, version = "1.0")
    protected ProductInfoSercvice productInfoSercvice;
}
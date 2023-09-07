package com.youngfinance.dataservice.service;

import com.youngfinance.api.service.RealNameService;
import com.youngfinance.api.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(interfaceClass = RealNameService.class, version = "1.0")
public class RealNameServiceImpl implements RealNameService {

    @Resource
    private UserService userService;

    @Override
    public boolean handleRealName(String phone, String name, String idCard) {
        // TODO: 接入身份证验证API

        // 更新实名认证信息
        boolean modify = userService.modifyRealName(phone, name, idCard);

        return modify;
    }
}

package com.youngfinance.dataservice.service;

import com.youngfinance.api.model.User;
import com.youngfinance.api.service.UserService;
import com.youngfinance.common.util.CommonUtil;
import com.youngfinance.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(interfaceClass = UserService.class, version = "1.0")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryByPhone(String phone) {
        User user = null;
        if(CommonUtil.checkPhone(phone)) {
            user = userMapper.selectByPhone(phone);
        }
        return user;
    }
}

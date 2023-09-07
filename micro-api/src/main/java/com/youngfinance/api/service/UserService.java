package com.youngfinance.api.service;

import com.youngfinance.api.model.User;

public interface UserService {

    // 判断手机号是否存在
    User queryByPhone(String phone);

    int userRegister(String phone, String password);

    User userLogin(String phone, String pword);

    boolean modifyRealName(String phone, String name, String idCard);
}

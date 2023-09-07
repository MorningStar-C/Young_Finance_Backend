package com.youngfinance.dataservice.service;

import com.youngfinance.api.model.FinanceAccount;
import com.youngfinance.api.model.User;
import com.youngfinance.api.service.UserService;
import com.youngfinance.common.util.CommonUtil;
import com.youngfinance.dataservice.mapper.FinanceAccountMapper;
import com.youngfinance.dataservice.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.utils.MD5Utils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.Executors;

@DubboService(interfaceClass = UserService.class, version = "1.0")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private FinanceAccountMapper financeAccountMapper;

    @Value("${youngFinance.config.salt}")
    private String salt;

    @Override
    public User queryByPhone(String phone) {
        User user = null;
        if(CommonUtil.checkPhone(phone)) {
            user = userMapper.selectByPhone(phone);
        }
        return user;
    }

    // 同步考虑线程安全
    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized int userRegister(String phone, String password) {
        int result = 0;
        if(CommonUtil.checkPhone(phone) && password != null && password.length() == 32) {

            User queryUser = userMapper.selectByPhone(phone);
            if(queryUser == null) {
                String saltPsd = DigestUtils.md5DigestAsHex((password + salt).getBytes(StandardCharsets.UTF_8));
                User user = new User();
                user.setPhone(phone);
                user.setLoginPassword(saltPsd);
                user.setAddTime(new Date());
                userMapper.insertReturnPrimaryKey(user);

                FinanceAccount financeAccount = new FinanceAccount();
                financeAccount.setUid(user.getId());
                financeAccount.setAvailableMoney(new BigDecimal("0"));
                financeAccountMapper.insertSelective(financeAccount);

                result = 1;
            } else {
                // 手机号存在
                result = 2;
            }

        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User userLogin(String phone, String password) {
        User user = null;
        if(CommonUtil.checkPhone(phone) && (password != null && password.length() == 32)) {
            String newPassword = DigestUtils.md5DigestAsHex((password + salt).getBytes(StandardCharsets.UTF_8));
            user = userMapper.selectLogin(phone, newPassword);
            // 更新最后的登陆时间
            if(user != null) {
                user.setLastLoginTime(new Date());
                userMapper.updateByPrimaryKeySelective(user);
            }
        }
        return user;
    }

    @Override
    public boolean modifyRealName(String phone, String name, String idCard) {
        int rows = 0;
        if(!StringUtils.isAnyBlank(phone, name, idCard)) {
            rows = userMapper.updateRealName(phone, name, idCard);
        }
        return rows > 0;
    }
}

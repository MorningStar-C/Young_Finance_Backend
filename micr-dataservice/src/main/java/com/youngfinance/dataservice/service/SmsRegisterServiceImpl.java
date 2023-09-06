package com.youngfinance.dataservice.service;

import com.youngfinance.api.service.SmsService;

import com.youngfinance.common.constants.RedisKey;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@DubboService(interfaceClass = SmsService.class, version = "1.0", group = "register")
public class SmsRegisterServiceImpl implements SmsService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean sendSms(String phone, String content) {
        // TODO: 封装阿里云短信SDK HttpClient发送请求并判断
        String code = RandomStringUtils.randomNumeric(4);
        boolean flag = false;
        if(code != null) {

            String key = RedisKey.KEY_SMS_CODE_REG + phone;
            stringRedisTemplate.boundValueOps(key).set(code, 3, TimeUnit.MINUTES);
            System.out.println("注册验证码：" + stringRedisTemplate.boundValueOps(key).get());
            flag = true;
        }

        return flag;
    }

    @Override
    public boolean checkSmsCode(String phone, String code) {
        String key = RedisKey.KEY_SMS_CODE_REG + phone;
        if(stringRedisTemplate.hasKey(key)) {
            String querySmsCode = stringRedisTemplate.boundValueOps(key).get();
            if(code.equals(querySmsCode)) {
                return true;
            }
        }
        return false;
    }
}

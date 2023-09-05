package com.youngfinance.front.controller;


import com.youngfinance.api.service.SmsService;
import com.youngfinance.common.constants.RedisKey;
import com.youngfinance.common.enums.RCode;
import com.youngfinance.common.util.CommonUtil;
import com.youngfinance.front.view.RespResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "短信业务")
@RestController
@RequestMapping("/v1/sms")
public class SmsController extends BaseController{

    // 发送注册验证码短信
    @GetMapping("/code/register")
    public RespResult sendCodeRegister(@RequestParam String phone) {
        RespResult result = RespResult.fail();
        if(CommonUtil.checkPhone(phone)) {
            // 判断redis中是否有该手机号的验证码
            String key = RedisKey.KEY_SMS_CODE + phone;
            if(stringRedisTemplate.hasKey(key)) {
                result = RespResult.ok();
                result.setRCode(RCode.SMS_CODE_EXISTS);
            } else {
                boolean isSendSms = smsService.sendSms(phone, "test");
                if(isSendSms) {
                    result = RespResult.ok();
                }
            }
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERROR);
        }
        return result;
    }
}

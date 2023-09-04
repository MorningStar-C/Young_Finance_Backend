package com.youngfinance.front.controller;


import com.youngfinance.front.view.RespResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "短信业务")
@RestController
@RequestMapping("/v1/sms")
public class SmsController extends BaseController{

    // 发送注册验证码短信
    @GetMapping("/code/register")
    public RespResult sendCodeRegister(@RequestParam String phone) {
        RespResult result = RespResult.fail();

        return result;
    }
}

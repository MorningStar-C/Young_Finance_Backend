package com.youngfinance.api.service;

public interface SmsService {
    // 发送短信
    boolean sendSms(String phone, String content);

    boolean checkSmsCode(String phone, String code);
}

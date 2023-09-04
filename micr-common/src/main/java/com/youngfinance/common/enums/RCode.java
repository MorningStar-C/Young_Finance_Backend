package com.youngfinance.common.enums;

public enum RCode {
    UNKNOWN(0, "请稍后重试"),
    SUCCESS(1000, "请求成功"),
    REQUEST_PARAM_ERROR(1001, "请求参数有误"),
    REQUEST_PRODUCT_TYPE_ERROR(1002, "产品类型参数有误"),
    PRODUCT_OFFLINE_ERROR(1003, "产品已经下线"),
    PHONE_FORMAT_ERROR(1004, "手机号格式不正确"),
    PHONE_EXISTS(1005, "手机号已注册")

    ;
    /**
     * 应答码
     * 0表示默认值
     * 1000-2000是请求参数有误，逻辑有问题
     * 2000-3000是服务器请求错误
     * 3000-4000是访问dubbo的应答结果
     */
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    private RCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

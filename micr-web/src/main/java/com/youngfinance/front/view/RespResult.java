package com.youngfinance.front.view;


import com.youngfinance.common.enums.RCode;

import java.sql.ResultSet;
import java.util.List;

/**
 * 统一的应答结果类。 controller方法的返回值都是它
 */
public class RespResult {
    // 应答码，自定义数字，结果标示
    private int code;

    // code的文字说明
    private String msg;

    private List list;

    private PageInfo page;

    // 单个数据
    private Object data;

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }



    // 表示成功的RespResult对象
    public static RespResult ok() {
        RespResult result = new RespResult();
        result.setRCode(RCode.SUCCESS);
        return result;
    }

    public static RespResult fail() {
        RespResult result = new RespResult();
        result.setRCode(RCode.UNKNOWN);
        return result;
    }

    public void setRCode(RCode rcode) {
        this.code = rcode.getCode();
        this.msg = rcode.getMsg();
    }


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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

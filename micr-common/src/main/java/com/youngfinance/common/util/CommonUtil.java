package com.youngfinance.common.util;

public class CommonUtil {
    // 处理PageNo
    public static int defaultPageNo(Integer pageNo) {
        int pNo = pageNo;
        if(pageNo == null || pageNo < 1) {
            pNo = 1;
        }
        return pNo;
    }

    // 处理pageSize
    public static int defaultPageSize(Integer pageSize) {
        int pSize = pageSize;
        if(pageSize == null || pageSize < 1) {
            pSize = 1;
        }

        return pSize;
    }

    // 手机号脱敏
    public static String phoneShield(String phone) {
        String res = "***********";
        if(phone != null && phone.trim().length() == 11) {
            res = phone.substring(0, 3) + "******" + phone.substring(9, 11);
        }
        return res;
    }
}

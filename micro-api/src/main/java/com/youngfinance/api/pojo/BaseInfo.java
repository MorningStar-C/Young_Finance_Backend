package com.youngfinance.api.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 计算利率、注册人数、累计成交金额
 */
public class BaseInfo implements Serializable {
    // 收益率平均值
    private BigDecimal historyAvgRate;

    // 累计成交金额
    private BigDecimal sumBidMoney;

    // 累计注册人数
    private Integer registerUsers;


    public BaseInfo() {
    }

    public BaseInfo(BigDecimal historyAvgRate, BigDecimal sumBidMoney, Integer registerUsers) {
        this.historyAvgRate = historyAvgRate;
        this.sumBidMoney = sumBidMoney;
        this.registerUsers = registerUsers;
    }

    /**
     * 获取
     * @return historyAvgRate
     */
    public BigDecimal getHistoryAvgRate() {
        return historyAvgRate;
    }

    /**
     * 设置
     * @param historyAvgRate
     */
    public void setHistoryAvgRate(BigDecimal historyAvgRate) {
        this.historyAvgRate = historyAvgRate;
    }

    /**
     * 获取
     * @return sumBidMoney
     */
    public BigDecimal getSumBidMoney() {
        return sumBidMoney;
    }

    /**
     * 设置
     * @param sumBidMoney
     */
    public void setSumBidMoney(BigDecimal sumBidMoney) {
        this.sumBidMoney = sumBidMoney;
    }

    /**
     * 获取
     * @return registerUsers
     */
    public Integer getRegisterUsers() {
        return registerUsers;
    }

    /**
     * 设置
     * @param registerUsers
     */
    public void setRegisterUsers(Integer registerUsers) {
        this.registerUsers = registerUsers;
    }

    public String toString() {
        return "BaseInfo{historyAvgRate = " + historyAvgRate + ", sumBidMoney = " + sumBidMoney + ", registerUsers = " + registerUsers + "}";
    }
}

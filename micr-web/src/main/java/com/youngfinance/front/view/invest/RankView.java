package com.youngfinance.front.view.invest;

public class RankView {
    private String phone;
    private Double money;


    public RankView(String phone, Double money) {
        this.phone = phone;
        this.money = money;
    }

    /**
     * 获取
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     * @return money
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置
     * @param money
     */
    public void setMoney(Double money) {
        this.money = money;
    }


}

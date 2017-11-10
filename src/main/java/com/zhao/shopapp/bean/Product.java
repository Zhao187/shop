package com.zhao.shopapp.bean;

import java.util.List;

/**
 * 商品信息数据
 */

public class Product {
    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 超级新手计划
         * money : 10
         * yearLv : 8.00
         * suodingDays : 30
         * minTouMoney : 100
         * memberNum : 100
         * progress : 50
         */

        private String id;
        private String name;
        private String money;
        private String yearLv;
        private String suodingDays;
        private String minTouMoney;
        private String memberNum;
        private String progress;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getYearLv() {
            return yearLv;
        }

        public void setYearLv(String yearLv) {
            this.yearLv = yearLv;
        }

        public String getSuodingDays() {
            return suodingDays;
        }

        public void setSuodingDays(String suodingDays) {
            this.suodingDays = suodingDays;
        }

        public String getMinTouMoney() {
            return minTouMoney;
        }

        public void setMinTouMoney(String minTouMoney) {
            this.minTouMoney = minTouMoney;
        }

        public String getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(String memberNum) {
            this.memberNum = memberNum;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", money='" + money + '\'' +
                    ", yearLv='" + yearLv + '\'' +
                    ", suodingDays='" + suodingDays + '\'' +
                    ", minTouMoney='" + minTouMoney + '\'' +
                    ", memberNum='" + memberNum + '\'' +
                    ", progress='" + progress + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}

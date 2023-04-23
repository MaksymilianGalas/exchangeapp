package com.exchanges.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeRate {
    private String table;
    private String currency;
    private String code;
    private Rate[] rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Rate[] getRates() {
        return rates;
    }

    public void setRates(Rate[] rates) {
        this.rates = rates;
    }

    public static class Rate {
        private String no;
        private String effectiveDate;
        private double mid;

        public String getNo() {
            return no;
        }

        @JsonProperty("no")
        public void setNo(String no) {
            this.no = no;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        @JsonProperty("effectiveDate")
        public void setEffectiveDate(String effectiveDate) {
            this.effectiveDate = effectiveDate;
        }

        public double getMid() {
            return mid;
        }

        @JsonProperty("mid")
        public void setMid(double mid) {
            this.mid = mid;
        }
    }
}

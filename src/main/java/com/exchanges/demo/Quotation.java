package com.exchanges.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quotation {

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
        private double bid;
        private double ask;

        @JsonCreator
        public Rate(@JsonProperty("no") String no, @JsonProperty("effectiveDate") String effectiveDate, @JsonProperty("bid") double bid, @JsonProperty("ask") double ask) {
            this.no = no;
            this.effectiveDate = effectiveDate;
            this.bid = bid;
            this.ask = ask;
        }


        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public void setEffectiveDate(String effectiveDate) {
            this.effectiveDate = effectiveDate;
        }

        public double getBid() {
            return bid;
        }

        public void setBid(double bid) {
            this.bid = bid;
        }

        public double getAsk() {
            return ask;
        }

        public void setAsk(double ask) {
            this.ask = ask;
        }
    }
}

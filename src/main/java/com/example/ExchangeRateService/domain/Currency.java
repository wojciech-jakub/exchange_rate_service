package com.example.ExchangeRateService.domain;

public class Currency {
    private String name;
    private Double rate;

    public Currency(String name, double rate){
        this.name = name;
        this.rate = rate;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }
}

package com.example.ExchangeRateService.domain;

import java.util.HashMap;
import java.util.Map;


public class RequestCounterCurrencies {

    private  Map<String, Integer> countedCurrencyRequests = new HashMap<>();

    private RequestCounterCurrencies() {
    }

    private static RequestCounterCurrencies requestCounterCurrencies;

    public static RequestCounterCurrencies getInstance() {
        if (requestCounterCurrencies == null) {
            requestCounterCurrencies = new RequestCounterCurrencies();
        }
        return requestCounterCurrencies;
    }

    public Map<String, Integer> getCountedCurrencyRequests() {
        return countedCurrencyRequests;
    }

    public void setCountedCurrencyRequests(Map<String, Integer> countedCurrencyRequests) {
        this.countedCurrencyRequests = countedCurrencyRequests;
    }


    public void incrementRequests(String name) {
        countedCurrencyRequests.put(name, countedCurrencyRequests.getOrDefault(name, 0) + 1);
    }

    public Integer getCountedRequests(String name) {
        return countedCurrencyRequests.getOrDefault(name, 0);
    }

}

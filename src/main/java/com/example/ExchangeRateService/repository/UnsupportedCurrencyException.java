package com.example.ExchangeRateService.repository;

public class UnsupportedCurrencyException extends RuntimeException {
    public UnsupportedCurrencyException(String unsupported_currency) {
    }
}

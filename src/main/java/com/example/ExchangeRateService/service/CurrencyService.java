package com.example.ExchangeRateService.service;

import com.example.ExchangeRateService.controller.CurrencyExchangeDto;

import java.util.List;

public interface CurrencyService {
    double exchangeCurrency(CurrencyExchangeDto currencyExchangeDto) throws RuntimeException;
    List<String> listCurrencies();
}


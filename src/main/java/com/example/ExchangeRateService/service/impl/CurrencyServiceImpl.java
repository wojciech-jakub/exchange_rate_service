package com.example.ExchangeRateService.service.impl;

import com.example.ExchangeRateService.controller.CurrencyExchangeDto;
import com.example.ExchangeRateService.repository.CurrencyRepository;
import com.example.ExchangeRateService.repository.UnsupportedCurrencyException;
import com.example.ExchangeRateService.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public double exchangeCurrency(CurrencyExchangeDto currencyExchangeDto) throws UnsupportedCurrencyException {
        double firstCurrencyRate = currencyRepository.getCurrencyRate(currencyExchangeDto.getFromCurrency());
        double secondCurrencyRate = currencyRepository.getCurrencyRate(currencyExchangeDto.getToCurrency());
        return currencyExchangeDto.getAmount() * (secondCurrencyRate / firstCurrencyRate);
    }

    @Override
    public List<String> listCurrencies() {
        return currencyRepository.getAllCurrencies();
    }
}

package com.example.ExchangeRateService.controller;

import com.example.ExchangeRateService.domain.RequestCounterCurrencies;
import com.example.ExchangeRateService.repository.UnsupportedCurrencyException;
import com.example.ExchangeRateService.service.CurrencyService;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/convert")
    public ResponseEntity<Double> exchangeCurrency(@RequestBody CurrencyExchangeDto currencyExchangeDto) {

            RequestCounterCurrencies.getInstance().incrementRequests(currencyExchangeDto.getFromCurrency());
            RequestCounterCurrencies.getInstance().incrementRequests(currencyExchangeDto.getToCurrency());
            try {
                Double amount = currencyService.exchangeCurrency(currencyExchangeDto);
                return ResponseEntity.ok().body(amount);
            } catch (UnsupportedCurrencyException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

    }

    @GetMapping("/currencies")
    public List<String> listCurrencies() {
        return currencyService.listCurrencies();
    }

    @GetMapping("/countedRequests")
    public Map<String, Integer> countedRequests() {
        return RequestCounterCurrencies.getInstance().getCountedCurrencyRequests();
    }


}

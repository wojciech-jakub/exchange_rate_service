package com.example.ExchangeRateService.repository;

import com.example.ExchangeRateService.service.CentralBankExchangeRates;
import com.example.ExchangeRateService.domain.Currency;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CurrencyRepository {
    private List<Currency> currencyArrayList = new ArrayList<>();

    public CurrencyRepository() {
        this.currencyArrayList = CentralBankExchangeRates.currencyFileXmlParser();
    }

    public double getCurrencyRate(String currencyName) throws UnsupportedCurrencyException {

        Optional<Currency> optionalCurrency = currencyArrayList.stream().filter(c -> c.getName().equals(currencyName))
            .findFirst();

        if(currencyName.equals("EUR")){
            return 1;
        }
        if(optionalCurrency.isPresent()){
            return optionalCurrency.get().getRate();
        }  else {
            throw new UnsupportedCurrencyException("Unsupported currency");
        }
    }


    public List<String> getAllCurrencies() {

        return currencyArrayList.stream().map(Currency::getName).collect(Collectors.toList());
    }

}

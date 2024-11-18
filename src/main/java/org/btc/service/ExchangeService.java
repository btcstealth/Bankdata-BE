package org.btc.service;

import org.btc.model.CurrencyAmount;
import org.btc.model.CurrencyExchange;

public interface ExchangeService {
    CurrencyExchange exchange(String fromCurrencyUnit, String toCurrencyUnit, double amount);

    double getExchangeRate(String fromCurrencyUnit, String toCurrencyUnit);
}

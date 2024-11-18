package org.btc.gateway;

import org.btc.model.CurrencyExchange;

public interface ExchangeGateway {
    CurrencyExchange exchange(String fromCurrency, String toCurrency, double amount);

    double getExchangeRate(String fromCurrency, String toCurrency);
}

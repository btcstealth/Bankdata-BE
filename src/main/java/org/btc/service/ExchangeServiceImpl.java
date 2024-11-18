package org.btc.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.btc.gateway.ExchangeGateway;
import org.btc.model.CurrencyExchange;

@ApplicationScoped
public class ExchangeServiceImpl implements ExchangeService {

    @Inject
    ExchangeGateway exchangeGateway;

    @Override
    public CurrencyExchange exchange(String fromCurrencyUnit, String toCurrencyUnit, double amount) {
        return exchangeGateway.exchange(fromCurrencyUnit, toCurrencyUnit, amount);
    }

    @Override
    public double getExchangeRate(String fromCurrencyUnit, String toCurrencyUnit) {
        return exchangeGateway.getExchangeRate(fromCurrencyUnit, toCurrencyUnit);
    }
}

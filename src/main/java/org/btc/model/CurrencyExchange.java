package org.btc.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@JsonSerialize(using = CurrencyExchangeSerializer.class)
public class CurrencyExchange {

    @Inject
    private CurrencyPair fromCurrency;
    @Inject
    private CurrencyPair toCurrency;

    public CurrencyPair getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(CurrencyPair fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public CurrencyPair getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(CurrencyPair toCurrency) {
        this.toCurrency = toCurrency;
    }

    @Override
    public String toString() {
        return "CurrencyExchange{" +
                "fromCurrency=" + fromCurrency +
                ", toCurrency=" + toCurrency +
                '}';
    }
}
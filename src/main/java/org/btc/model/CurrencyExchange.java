package org.btc.model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CurrencyExchange {

    @Inject
    private CurrencyAmount fromCurrency;
    @Inject
    private CurrencyAmount toCurrency;

    public CurrencyAmount getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(CurrencyAmount fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public CurrencyAmount getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(CurrencyAmount toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String toJson() {
        //TODO: Change conversion via json format library instead likely related to jackson in separate class transformer
        //TODO: Remember to fix to danish format for decimals: ',' instead of '.'.
        return String.format("{ \"%s\": %s, \"%s\": %s }",
                getFromCurrency().getCurrencyUnit(),
                getFromCurrency().getAmount(),
                getToCurrency().getCurrencyUnit(),
                getToCurrency().getAmount()
        );
    }

    @Override
    public String toString() {
        return "Exchange" + toJson();
    }
}

package org.btc.model;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CurrencyAmount {
    private String currencyUnit;
    private double amount;

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

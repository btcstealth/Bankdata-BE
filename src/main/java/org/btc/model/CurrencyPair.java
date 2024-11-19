package org.btc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CurrencyPair {
    //@JsonFormat(shape = JsonFormat.Shape.String)
    private String currencyUnit;

    @JsonFormat(locale = "da_DK", shape = JsonFormat.Shape.STRING, pattern = "#,##0.00")
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

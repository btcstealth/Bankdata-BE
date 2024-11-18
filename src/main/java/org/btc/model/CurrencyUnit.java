package org.btc.model;

import jakarta.enterprise.context.ApplicationScoped;

//TODO: Reintroduce this instead of the string format along with the customer SupportedCurrencyValidator.
@ApplicationScoped
public enum CurrencyUnit {
    DKK, EUR, USD
}

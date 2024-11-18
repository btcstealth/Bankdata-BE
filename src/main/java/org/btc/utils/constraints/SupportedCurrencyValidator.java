package org.btc.utils.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.btc.model.CurrencyUnit;

import java.util.Arrays;
import java.util.List;

public class SupportedCurrencyValidator implements ConstraintValidator<CustomConstraint, CurrencyUnit> {

    private List<CurrencyUnit> supportedCurrencies;

    @Override
    public boolean isValid(CurrencyUnit currencyUnit, ConstraintValidatorContext constraintValidatorContext) {
        return supportedCurrencies.contains(currencyUnit);
    }

    @Override
    public void initialize(CustomConstraint constraintAnnotation) {
        this.supportedCurrencies = Arrays.asList(CurrencyUnit.values());
    }
}

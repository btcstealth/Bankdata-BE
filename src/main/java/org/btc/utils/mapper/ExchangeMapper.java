package org.btc.utils.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Singleton;
import org.btc.model.CurrencyPair;
import org.btc.model.CurrencyExchange;

@Singleton
public class ExchangeMapper {
    public double getExchangeConversionRate(String jsonResponseStr) throws JsonProcessingException {
        //fix proper dependency injection for this, @Inject wasn't the way to go.
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponseStr);
        return Double.parseDouble(jsonNode.get("conversion_rate").toString());
    }

    public CurrencyExchange mapToCurrencyExchange(String fromCurrency, String toCurrency, double fromAmount, double toAmount) {
        CurrencyPair fromCurrencyAmount = new CurrencyPair();
        fromCurrencyAmount.setCurrencyUnit(fromCurrency);
        fromCurrencyAmount.setAmount(fromAmount);
        CurrencyPair toCurrencyAmount = new CurrencyPair();
        toCurrencyAmount.setCurrencyUnit(toCurrency);
        toCurrencyAmount.setAmount(toAmount);
        CurrencyExchange currencyExchange = new CurrencyExchange();
        currencyExchange.setFromCurrency(fromCurrencyAmount);
        currencyExchange.setToCurrency(toCurrencyAmount);
        return currencyExchange;
    }
}

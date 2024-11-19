package org.btc.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CurrencyExchangeSerializer extends JsonSerializer<CurrencyExchange> {
    @Override
    public void serialize(CurrencyExchange currencyExchange, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.of("da_DK"));
        DecimalFormat df = new DecimalFormat("#,##0.00"); //symbols

        jsonGenerator.writeStringField(
                currencyExchange.getFromCurrency().getCurrencyUnit(),
                df.format(currencyExchange.getFromCurrency().getAmount())
        );
        jsonGenerator.writeStringField(
                currencyExchange.getToCurrency().getCurrencyUnit(),
                df.format(currencyExchange.getToCurrency().getAmount())
        );
        jsonGenerator.writeEndObject();
    }
}

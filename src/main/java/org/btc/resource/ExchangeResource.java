package org.btc.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*; //TODO: fix * import into separate ones, check intellij settings
import jakarta.ws.rs.core.MediaType;
import org.btc.model.CurrencyExchange;
import org.btc.service.ExchangeService;

@Path("/exchange")
public class ExchangeResource {
    @Inject
    ExchangeService exchangeService;

    //TODO: This path can be done better.
    @GET
    @Path("{fromCurrency}/{toCurrency}/funds/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CurrencyExchange exchangeCurrency(
            @PathParam("fromCurrency") String fromCurrency,
            @PathParam("toCurrency") String toCurrency,
            @PathParam("amount") double amount) {
        //TODO: Would be better to handle the json format conversion part here with libraries instead, eg. jackson.

        return exchangeService.exchange(fromCurrency, toCurrency, amount);
    }

    @GET
    @Path("/rate/{fromCurrency}/{toCurrency}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public double getExchangeRate(@PathParam("fromCurrency") String fromCurrency, @PathParam("toCurrency") String toCurrency) {
        return exchangeService.getExchangeRate(fromCurrency, toCurrency);
    }
}

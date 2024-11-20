package org.btc.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.btc.model.CurrencyExchange;
import org.btc.service.ExchangeService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Path("/exchange")
public class ExchangeResource {
    @Inject
    ExchangeService exchangeService;

    //TODO: Consider making amount an option field on the other endpoint instead.
    @GET
    @Path("/{fromCurrency}/{toCurrency}/{fundsAmount}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Exchange an amount between two currencies")
    public CurrencyExchange exchangeCurrency(
            @Parameter(description = "The currency unit to exchange from", required = true, schema = @Schema(example = "DKK"))
            @PathParam("fromCurrency") String fromCurrency,
            @Parameter(description = "The currency unit to exchange to", required = true, schema = @Schema(example = "USD"))
            @PathParam("toCurrency") String toCurrency,
            @Parameter(description = "The amount of currency to exchange", required = true, schema = @Schema(example = "100.0"))
            @PathParam("fundsAmount") double fundsAmount) {
        //TODO: Would be better to handle the json format conversion part here with libraries instead, eg. jackson.
        return exchangeService.exchange(fromCurrency, toCurrency, fundsAmount);
    }

    @GET
    @Path("/rate/{fromCurrency}/{toCurrency}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get current exchange rate between two currencies")
    public double getExchangeRate(
            @Parameter(description = "The currency unit to exchange from", required = true, schema = @Schema(example = "DKK"))
            @PathParam("fromCurrency") String fromCurrency,
            @Parameter(description = "The currency unit to exchange to", required = true, schema = @Schema(example = "USD"))
            @PathParam("toCurrency") String toCurrency) {
        return exchangeService.getExchangeRate(fromCurrency, toCurrency);
    }
}

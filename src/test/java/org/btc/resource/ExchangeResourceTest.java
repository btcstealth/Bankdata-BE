package org.btc.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ExchangeResourceTest {
    @Test
    void testGetExchangeRateEndpoint() {
        // arrange
        String fromCurrency = "DKK";
        String toCurrency = "USD";

        //Can only validate on the statusCode since the exchange rate could change over time.
        given()
                .pathParam("fromCurrency", fromCurrency)
                .pathParam("toCurrency", toCurrency)
                .when().get("exchange/rate/{fromCurrency}/{toCurrency}")
                .then()
                    .statusCode(200);
    }

    @Test
    void testGetExchangeRateEndpointInvalidCurrencyCode() {
        // arrange
        String fromCurrency = "NARK";
        String toCurrency = "USD";

        // act and assert
        given()
                .pathParam("fromCurrency", fromCurrency)
                .pathParam("toCurrency", toCurrency)
                .when().get("/exchange/rate/{fromCurrency}/{toCurrency}")
                .then()
                    .statusCode(400);
    }

    @Test
    void testExchangeCurrencyEndpoint() {
        // arrange
        final String fromCurrency = "DKK";
        final String toCurrency = "USD";
        final double amount = 100.0;
        final String expectedBody = "{\"DKK\":\"100,00\",\"USD\":\"14,61\"}";
        //final String expectedBody = "{ \"DKK\": 100,00, \"USD\": 14,61 }";

        //GET http://localhost:8080/exchange/DKK/USD/100
        given()
                .pathParam("fromCurrency", fromCurrency)
                .pathParam("toCurrency", toCurrency)
                .pathParam("fundsAmount", amount)
                .when().get("/exchange/{fromCurrency}/{toCurrency}/{fundsAmount}")
                .then()
                    .statusCode(200)
                .body(is(expectedBody));
    }
}

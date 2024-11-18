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
        // act and assert
        //TODO: arrange call correctly with path params, currently getting 404 not found when running
        //"/rate/{fromCurrency}/{toCurrency}"

        //Can only validate on the statusCode since the exchange rate could change over time.
        given()
            .when().get("/rate/DKK/USD")
                .then()
                    .statusCode(200);
    }

    @Test
    void testGetExchangeRateEndpointInvalidCurrencyCode() {
        // arrange
        String expectedBody = "";
        // act and assert
        //TODO: arrange call with path params
        given()
            .when().get("/rate/{fromCurrency}/{toCurrency}")
                .then()
                    .statusCode(400);
    }

    @Test
    void testexchangeCurrencyEndpoint() {
        // arrange
        final String fromCurrency = "DKK";
        final String toCurrency = "USD";
        final double amount = 100.0;
        final String expectedBody = "{ \"DKK\": 100,00, \"USD\": 14,61 }";
        // act and assert
        //TODO: arrange call with path params
        //GET http://localhost:8080/exchange/DKK/USD/funds/100

        given()
            .when().get("/exchange/{fromCurrency}/{toCurrency}/funds/{amount}")
                .then()
                    .statusCode(200)
                .body(is(expectedBody));
    }
}

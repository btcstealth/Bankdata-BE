package org.btc.resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
                .statusCode(500);
        //TODO: should be changed to return 400, since it should reflect invalid input.
    }

    @Test
    void testExchangeCurrencyEndpoint() {
        // arrange
        final String fromCurrency = "DKK";
        final String toCurrency = "USD";
        final double amount = 100.0;

        //GET http://localhost:8080/exchange/DKK/USD/100
        String currencyExchangeRes = given()
                .pathParam("fromCurrency", fromCurrency)
                .pathParam("toCurrency", toCurrency)
                .pathParam("fundsAmount", amount)
                .when().get("/exchange/{fromCurrency}/{toCurrency}/{fundsAmount}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        // ideal would be to implement a deserializer for CurrencyExchange.java
        Map<String, String> currencyExchangeMap = toCurrencyExchange(currencyExchangeRes);
        assertTrue(currencyExchangeMap.containsKey(fromCurrency));
        assertTrue(currencyExchangeMap.containsKey(toCurrency));

        // lazily replacement of comma to dot, alternative is using format
        assertEquals(amount,
                currencyExchangeMap.get(fromCurrency).contains(",") ?
                        Double.parseDouble(currencyExchangeMap.get(fromCurrency).replace(",", ".")) :
                        Double.parseDouble(currencyExchangeMap.get(fromCurrency)));
    }

    private Map<String, String> toCurrencyExchange(String jsonBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonBody, new TypeReference<HashMap<String, String>>() {});
        } catch (Exception e) {
            assertFalse(true);
        }
        //CurrencyExchange
        return new HashMap<>();
    }
}

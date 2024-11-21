package org.btc.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.btc.utils.exceptionhandling.GenericException;
import org.btc.utils.mapper.ExchangeMapper;
import org.btc.model.CurrencyExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

@Singleton
public class ExchangeGatewayImpl implements ExchangeGateway {
    private static final Logger logger = Logger.getLogger(ExchangeGatewayImpl.class.getName());
    private static final String requestUrl = "https://v6.exchangerate-api.com/v6/";

    @Inject
    private ExchangeMapper exchangeMapper;

    private Properties properties;

    private String apiKey;

    public ExchangeGatewayImpl() {
        //beautify this.
        try {
            this.apiKey = "9f4cf4f410820d6d9f8073f6";
            logger.info("API key: " + this.apiKey);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    //TODO: update this with working injection of properties or delete it.
    private String getApiKey() {
        try {
            java.io.InputStream is = this.getClass().getResourceAsStream("my.properties");

            //figure out dependency injection for properties
            properties.load(is);

            return properties.getProperty("api.key");
        } catch (IOException ioe) {
            throw new GenericException(ioe.getMessage());
        }
    }

    @Override
    public CurrencyExchange exchange(String fromCurrency, String toCurrency, double originalAmount) {
        final String exchangeUrl = String.format("%s/%s/pair/%s/%s/%s", requestUrl, apiKey, fromCurrency, toCurrency, originalAmount + "");
        logger.info("request for exchange(...): " + exchangeUrl);
        return exchangeMapper.mapToCurrencyExchange(
                fromCurrency,
                toCurrency,
                originalAmount,
                parseCurrencyConversionResult(performRequest(exchangeUrl, "GET"))
        );
    }

    private double parseCurrencyConversionResult(String jsonResponseStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonResponseStr);
            return Double.parseDouble(jsonNode.get("conversion_result").toString());
        } catch (JsonProcessingException jpe) {
            throw new GenericException();
        }
    }

    @Override
    public double getExchangeRate(String fromCurrency, String toCurrency) {
        final String exchangeRateUrl = String.format("%s/%s/pair/%s/%s", requestUrl, apiKey, fromCurrency, toCurrency);
        logger.info("request for getExchangeRate(...): " + exchangeRateUrl);
        String response = performRequest(exchangeRateUrl, "GET");

        try {
            double exchangeConversionRate = exchangeMapper.getExchangeConversionRate(response);
            return exchangeConversionRate;
        } catch (Exception e) {
            throw new GenericException();
        }
    }

    private String performRequest(String requestUrl, String requestMethod) {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethod);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                //log error and throw exception, potentially custom exception
                throw new GenericException();
            }
        } catch (Exception e) {
            //log error and throw exception, potentially custom exception
            logger.info("Error: " + e);
            throw new GenericException(e.getMessage());
        }
    }
}

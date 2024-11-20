package org.btc.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.btc.model.Account;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class AccountResourceTest {
    @Test
    void testGetAllAccountsEndpoint() {
        given()
                .when().get("/account")
                .then()
                .statusCode(200);
    }

    @Test
    void testCreateAccountEndpoint() {
        // arrange
        long accountNumber = 234567891;
        Account account =
                new Account().withAccountNumber(accountNumber).withFirstName("Søren").withLastName("Anneberg").withCurrencyUnit("DKK").withBalance(2000000.0);

        // act and assert
        given()
                .contentType(ContentType.JSON)
                .body(account)
                .when().post("/account")
                .then()
                .statusCode(201)
                .body(is(notNullValue()));
    }

    @Test
    void testDepositFundsEndpoint() {
        // arrange
        long accountNumber = 112345678;
        double toDepositFunds = 1000000;
        String expectedTotalBalance = "3500000.0";

        // deposit funds to bank account.
        given()
                .pathParam("accountNumber", accountNumber)
                .pathParam("funds", toDepositFunds)
                .when().patch("/account/{accountNumber}/deposit/{funds}")
                .then()
                .statusCode(204);

        // check that the total balance is correct after deposit.
        given()
                .pathParam("accountNumber", accountNumber)
                .when().get("/account/{accountNumber}/balance")
                .then()
                .statusCode(200)
                .body(is(expectedTotalBalance));
    }

    @Test
    void testTransferFundsEndpoint() {
        // arrange
        double transferAmount = 450000.0;
        int senderAccountNumber = 112345678;
        double senderExpectedBalance = (2500000.0 - transferAmount);
        int receiverAccountNumber = 193345678;
        double receiverExpectedBalance = (1250000.0 + transferAmount);
        double funds = 450000.0;

        // transfer funds and confirm success
        given()
                .pathParam("senderAccountNumber", senderAccountNumber)
                .pathParam("receiverAccountNumber", receiverAccountNumber)
                .pathParam("funds", funds)
                .when().patch("/account/{senderAccountNumber}/receiver/{receiverAccountNumber}/funds/{funds}")
                .then()
                .statusCode(204);

        // verify that account balances are correct after transfer for sender
        double senderBalance = given()
                .pathParam("accountNumber", senderAccountNumber)
                .when().get("/account/{accountNumber}/balance")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(double.class);

        assertEquals(senderExpectedBalance, senderBalance);

        // verify that account balances are correct after transfer for sender
        double receiverBalance = given()
                .pathParam("accountNumber", receiverAccountNumber)
                .when().get("/account/{accountNumber}/balance")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(double.class);

        assertEquals(receiverExpectedBalance, receiverBalance);
    }

    @Test
    void testGetBalanceEndpoint() {
        final long accountNumber = 112345678;
        final String expectedBalance = "2500000.0";
        given()
                .pathParam("accountNumber", accountNumber)
                .when().get("/account/{accountNumber}/balance")
                .then()
                .statusCode(200)
                .body(is(expectedBalance));
    }
}

package org.btc.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.btc.model.Account;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

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
        Account expectedAccount = account;

        final Account expectedAccentPresentAfterCreate = account;
        
        // act and assert
        //figure out how to add input
        given()
                //.param(account)
                .when().post("/account")
                .then()
                .statusCode(200);
                //.body(is(expectedAccount));

        //deal with http 415, unsupported media type
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
        final int senderAccountNumber = 112345678;
        final int receiverAccountNumber = 183345678;

        given()
                .when().patch("/account/{senderAccountNumber}/receiver/{receiverAccountNumber}/funds/{funds}")
                .then()
                .statusCode(204);

        // Can be argued to assert on updated account balance on both accounts as well, but need to verify if h2 persistence supports this before adding logic.
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

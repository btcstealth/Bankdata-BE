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
        final String expectedBody = "";

        given()
                .when().get("/account")
                .then()
                .statusCode(200);
                //.body(is(expectedBody));
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
        //provide the account as input and likely need another type of body comparison ignoring id, if the ids are auto generated.
        given()
                .when().post("/account")
                .then()
                .statusCode(200);
                //.body(is(expectedAccount));

        //deal with http 415, unsupported media type
    }

    @Test
    void testDepositFundsEndpoint() {
        given()
                .when().patch("{accountNumber}/deposit/{funds}")
                .then()
                .statusCode(204);

        // Can be argued to assert on updated account balance as well.
    }

    @Test
    void testTransferFundsEndpoint() {
        final int senderAccountNumber = 112345678;
        final int receiverAccountNumber = 183345678;

        given()
                .when().patch("{senderAccountNumber}/receiver/{receiverAccountNumber}/funds/{funds}")
                .then()
                .statusCode(204);

        // Can be argued to assert on updated account balance on both accounts as well, but need to verify if h2 persistence supports this before adding logic.
    }

    @Test
    void testGetBalanceEndpoint() {
        final int accountNumber = 112345678;
        final double expectedBalance = 1525000.0;
        given()
            .when().get("/{accountNumber}/balance")
            .then()
            .statusCode(200)
                .body(is(expectedBalance));
    }
}

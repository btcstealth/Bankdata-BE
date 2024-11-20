# Bankdata-BE
Java backend

## Dependencies
- Java
- Maven

## How to run
- Run with command in cmd: ./mvnw compile quarkus:dev
- Navigate to: localhost:8080/swagger-ui for an easy overview of the endpoints
- Pick an endpoint and click 'Try it out', relevant input data that runs should already be suggested as an example.
- Click 'Execute' and the response body will be shown along with http code below 

## Endpoints and functionality
### Account endpoints:
GET /account : Get all account saved in database, implemented for testing purposes.

POST /account : Create a account, which is added to database

GET /account/{accountNumber}/balance : Get the balance for the provided account specified by account number

PATCH /account/{accountNumber}/deposit/{funds} : Deposit funds into the provided account specified by account number

PATCH /account/{senderAccountNumber}/receiver/{receiverAccountNumber}/funds/{funds} : Transfer funds between two accounts

### Exchange endpoints:

GET /exchange/rate/{fromCurrency}/{toCurrency} : Get current exchange rate between two currencies

GET /exchange/rate/{fromCurrency}/{toCurrency}/{amount} : Exchange an amount between two currencies

### Curl examples for endpoints:
....

## Considerations
Custom exceptions and error handling:

Ignored package vulnerbilities:

## TODO
Return exchange object with proper danish formatting for endpoint: GET /exchange/rate/{fromCurrency}/{toCurrency}/{amount}
eg.

{
  "DKK": 100,00,
  "USD": 14,20
}

Rather than:
 
{
  "DKK": "100,00",
  "USD": "14,20"
}


Proper dao/database layer handling

# Bankdata-BE
Java backend

## Dependencies
- Java
- Maven

## How to run
- Run with command in cmd: ./mvnw compile quarkus:dev from project root.
- Navigate to: localhost:8080/swagger-ui for an easy overview of the endpoints or access for instance Postman via url: localhost:8080/{full endpointUrl}.
- Pick an endpoint and click 'Try it out', relevant input data that runs should already be suggested as an example.
- Click 'Execute' and the response body will be shown along with http code below.
- If running endpoints tests, make sure to run test at a time. The current setup does not support multiple due to the h2 setup, it can't currently reset til database for each test.

## Endpoints and functionality
### Account endpoints:
GET /account : Get all account saved in database, implemented for testing purposes.

POST /account : Create a account, which is added to database

GET /account/{accountNumber}/balance : Get the balance for the provided account specified by account number

PATCH /account/{accountNumber}/deposit/{funds} : Deposit funds into the provided account specified by account number

PATCH /account/{senderAccountNumber}/receiver/{receiverAccountNumber}/funds/{funds} : Transfer funds between two accounts

### Exchange endpoints:

GET /exchange/rate/{fromCurrency}/{toCurrency} : Get current exchange rate between two currencies

GET /exchange/rate/{fromCurrency}/{toCurrency}/{fundsAmount} : Exchange an amount between two currencies

### Curl examples for endpoints:
AccountResource:
curl -X 'GET' \
  'http://localhost:8080/account' \
  -H 'accept: application/json'


curl -X 'POST' \
  'http://localhost:8080/account' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "accountNumber": 234567891,
  "firstName": "Mette",
  "lastName": "Jensen",
  "balance": 2450000,
  "currencyUnit": "DKK"
}'


curl -X 'GET' \
  'http://localhost:8080/account/123345678/balance' \
  -H 'accept: application/json'


curl -X 'PATCH' \
  'http://localhost:8080/account/123345678/deposit/1000000' \
  -H 'accept: */*'


curl -X 'PATCH' \
  'http://localhost:8080/account/112345678/receiver/193345678/funds/450000' \
  -H 'accept: */*'

ExchangeResource:
curl --location 'http://localhost:8080/exchange/DKK/USD/100'
curl --location 'http://localhost:8080/exchange/DKK/USD/100'

## Considerations
Custom exceptions and error handling:

## TODO
### Danish double or BigDecimal format, and JSON serialization
- Return exchange object with proper danish double formatting for endpoint: GET /exchange/rate/{fromCurrency}/{toCurrency}/{amount}
eg.

{
  "DKK": 100,00,
  "USD": 14,20
}
rather than format 
{
  "DKK": "100,00",
  "USD": "14,20"
}

Generally I would prefer to return the ordinary english format:
{
  "DKK": 100.00,
  "USD": 14.20
}
and let the frontend be in charge of what is presented to the end user...

### Proper dao/database layer handling
-   Atomic batch request for multiple updates: transfor funds
-   Refactoring, particular around the connection object

  
###  Proper exception handling
-   Various ErrorCodes with different message responses depending on the scenario
-   Refactor to get rid of the try catch cluttering

### Consider proper use of quarkus object annotation in relation to dependency injection
- @ApplicationScoped, @Dependent, @Singleton and custom ones ...
Has not been a priority given first use of Quarkus, along with a lot of time fiddling with the h2 configuration and setup.

### Ignored package vulnerbilities


### Avoid hardcoded api key and potententially inline sql
- Api key in hosted environment variable or settings.xml
- ORM / mapper instead of SQL for database access

### Improve tests
- Find solution for clearing persistent database storage between each test, such that all tests can be run in sequence from the class
- Add more negative / edge cases for tests.
- Added unit / behavioural tests for service and dao layer.

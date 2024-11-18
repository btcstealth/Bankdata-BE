package org.btc.utils.exceptionhandling;

//TODO: Implement a Java Exception Runtime Mapping to handle these different error codes with tailored messages and log the description string.
public enum ErrorCode {
    unsupportedCurrencyCode(1, "The provided currency code is not supported"),
    accountDoesNotExist(2, "No account with the provided account number exists");

    private final int code;
    private final String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

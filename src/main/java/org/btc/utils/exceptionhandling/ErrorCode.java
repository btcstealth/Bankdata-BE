package org.btc.utils.exceptionhandling;

public enum ErrorCode {
    unsupportedCurrencyCode("BD-1", "The provided currency code is not supported"),
    accountDoesNotExist("BD-2", "No account with the provided account number exists");

    private final String code;
    private final String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "{" +
                "code:" + code +
                ", description:'" + description + '\'' +
                '}';
    }
}

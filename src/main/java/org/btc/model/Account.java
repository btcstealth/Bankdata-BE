package org.btc.model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@ApplicationScoped
@Entity
public class Account {

    @Id
    private Long accountNumber;
    @NotBlank(message = "Firstname may not be blank")
    private String firstName;
    @NotBlank(message = "Lastname may not be blank")
    private String lastName;
    //@NotBlank(message = "Balance has to be defined")
    private double balance;
    @NotBlank(message = "Currency unit has to be defined")
    private String currencyUnit;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Account withAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Account withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Account withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Account withBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }
    public Account withCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
        return this;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                ", currencyUnit='" + currencyUnit + '\'' +
                '}';
    }
}

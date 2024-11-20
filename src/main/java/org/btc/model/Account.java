package org.btc.model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@ApplicationScoped
@Entity
public class Account {

    @Id
    @Column(name = "account_number", unique = true)
    @Schema(example = "234567891", required = true)
    private Long accountNumber;
    @NotBlank(message = "Firstname may not be blank")
    @Column(name = "first_name")
    @Schema(example = "Mette", required = true)
    private String firstName;
    @NotBlank(message = "Lastname may not be blank")
    @Column(name = "last_name")
    @Schema(example = "Jensen", required = true)
    private String lastName;
    @Schema(example = "2450000", required = true)

    private double balance;
    @NotBlank(message = "Currency unit has to be defined")
    @Column(name = "currency_unit")
    @Schema(example = "DKK")
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

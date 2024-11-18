package org.btc.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.btc.model.Account;

import java.util.List;

@ApplicationScoped
public class AccountServiceImpl implements AccountService {

    @Override
    public List<Account> getAccounts(int accountNumber) {
        return null;
    }

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public void depositIntoAccount(int accountNumber, double fundsAmount) {
    }

    @Override
    public void transferFunds(int senderAccountNumber, int retrieverAccountNumber, double amount) {

    }

    @Override
    public double getAccountBalance(int accountNumber) {
        return getAccount(accountNumber).getBalance();
    }

    private Account getAccount(int accountNumber) {
        //In case the accountNumber doesn't exist, throw a runtime exception with a customer error code from the gateway level.
        //Should no allowed the consumer to continuously attempt guessing potential accountNumbers, even with later auth and rate limiting.
        return null;
    }
}

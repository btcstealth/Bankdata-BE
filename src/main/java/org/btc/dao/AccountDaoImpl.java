package org.btc.dao;

import jakarta.enterprise.context.ApplicationScoped;
import org.btc.model.Account;

import java.util.List;

@ApplicationScoped
public class AccountDaoImpl implements AccountDao {

    @Override
    public List<Account> getAllAccounts(int accountNumber) {
        return null;
    }

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public void depositIntoAccount(int accountNumber, double fundsAmount) {
        if (!accountExists(accountNumber)) {
            throw new RuntimeException();
        }
    }

    @Override
    public void transferFunds(int senderAccountNumber, int retrieverAccountNumber, double amount) {

    }

    private boolean accountExists(int accountNumber) {
        return getAccount(accountNumber) != null;
    }

    @Override
    public Account getAccount(int accountNumber) {
        //implement h2 database access here
        return null;
    }
}

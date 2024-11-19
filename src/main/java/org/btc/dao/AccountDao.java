package org.btc.dao;

import org.btc.model.Account;

import java.util.List;

public interface AccountDao {
    Account getAccount(int accountNumber);

    List<Account> getAllAccounts();
    Account createAccount(Account account);

    void depositIntoAccount(int accountNumber, double fundsAmount);

    void transferFunds(int senderAccountNumber, int retrieverAccountNumber, double amount);
}

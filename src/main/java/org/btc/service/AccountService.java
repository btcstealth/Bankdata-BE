package org.btc.service;

import org.btc.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account createAccount(Account account);

    void depositIntoAccount(int accountNumber, double fundsAmount);

    void transferFunds(int senderAccountNumber, int retrieverAccountNumber, double amount);

    double getAccountBalance(int accountNumber);

}

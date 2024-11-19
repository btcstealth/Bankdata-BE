package org.btc.service;

import org.btc.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account createAccount(Account account);

    void depositIntoAccount(Long accountNumber, double fundsAmount);

    void transferFunds(Long senderAccountNumber, Long retrieverAccountNumber, double amount);

    double getAccountBalance(Long accountNumber);

}

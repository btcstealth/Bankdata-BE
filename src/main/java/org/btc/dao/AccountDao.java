package org.btc.dao;

import org.btc.model.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {
    Account getAccount(Long accountNumber);

    List<Account> getAllAccounts();
    Account createAccount(Account account);

    void updateAccountBalance(Long accountNumber, double newBalance);

    void transferFundsBatch(Long senderAccountNumber, Long retrieverAccountNumber, double amount);

    double getAccountBalance(Long accountNumber) throws SQLException;

}

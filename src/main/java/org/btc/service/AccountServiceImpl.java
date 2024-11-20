package org.btc.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.btc.dao.AccountDao;
import org.btc.model.Account;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class AccountServiceImpl implements AccountService {

    @Inject
    AccountDao accountDao;

    @Override
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @Override
    public Account createAccount(Account account) {
        return accountDao.createAccount(account);
    }

    @Override
    public void depositIntoAccount(Long accountNumber, double fundsAmount) {
        accountDao.updateAccountBalance(accountNumber, this.getAccountBalance(accountNumber) + fundsAmount);
    }

    @Override
    public void transferFunds(Long senderAccountNumber, Long retrieverAccountNumber, double fundsAmount) {
        if (isAllowedToPerformTransfer(senderAccountNumber, retrieverAccountNumber, fundsAmount)) {
            accountDao.transferFunds(senderAccountNumber, retrieverAccountNumber, fundsAmount);
        }
    }

    private boolean isAllowedToPerformTransfer(Long senderAccountNumber, Long retrieverAccountNumber, double fundsAmount) {
        return accountExists(senderAccountNumber) &&
                accountExists(retrieverAccountNumber) &&
                isAuthenticatedForSenderAccount() &&
                hasBalanceForTransfer(senderAccountNumber, fundsAmount);
    }


    private boolean hasBalanceForTransfer(Long senderAccountNumber, double fundsAmount) {
        return this.getAccountBalance(senderAccountNumber) >= fundsAmount;
    }

    private boolean accountExists(Long accountNumber) {
        return accountDao.getAccount(accountNumber) != null;
    }


    @Override
    public double getAccountBalance(Long accountNumber) {
        if (isAuthenticatedForSenderAccount()) {
            try {
                return accountDao.getAccountBalance(accountNumber);
            } catch (SQLException sqlException) {
                throw new RuntimeException();
            }
        } else {
            // throw some authentication error
            throw new RuntimeException();
        }
    }

    private boolean isAuthenticatedForSenderAccount() {
        //TODO: implement some authentication logic
        return true;
    }
}

package org.btc.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.btc.dao.AccountDao;
import org.btc.model.Account;
import org.btc.utils.exceptionhandling.GenericException;

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
            //TODO: ideally this should be performed as a single batch transaction with atomicity instead.
            try {
                //update account balance for sender account
                accountDao.updateAccountBalance(senderAccountNumber, accountDao.getAccountBalance(senderAccountNumber) - fundsAmount);

                //update account balance for receiver account
                accountDao.updateAccountBalance(retrieverAccountNumber, accountDao.getAccountBalance(retrieverAccountNumber) + fundsAmount);
            } catch (SQLException sqlException) {
                throw new GenericException();
            }
        }
    }

    private boolean isAllowedToPerformTransfer(Long senderAccountNumber, Long retrieverAccountNumber, double fundsAmount) {
        return hasBalanceForTransfer(senderAccountNumber, fundsAmount) &&
                accountExists(retrieverAccountNumber) &&
                isAuthenticatedForSenderAccount();
    }

    private boolean hasBalanceForTransfer(Long senderAccountNumber, double sendAmount) {
        try {
            // check that account balance for sender contains the amount
            return (accountDao.getAccountBalance(senderAccountNumber) - sendAmount) >= 0;
        } catch (SQLException sqlException) {
            //doesn't have the funds ErrorCode...
            throw new GenericException(sqlException.getMessage());
        }
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

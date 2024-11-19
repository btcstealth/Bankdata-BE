package org.btc.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.btc.model.Account;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AccountDaoImpl implements AccountDao {

    @Inject
    DataSource dataSource;

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Account");
            while (resultSet.next()) {
                accountList.add(prepareAccount(resultSet));
            }
            return accountList;
        } catch (Exception e) {
            //potentially handle differently
            throw new RuntimeException(e.getMessage());
        }
    }

    private Account prepareAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        Long accountNumber = resultSet.getLong(1);
        account.setAccountNumber(accountNumber);
        String firstName = resultSet.getString(2);
        account.setFirstName(firstName);

        String lastName = resultSet.getString(3);
        account.setLastName(lastName);
        return account;
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

package org.btc.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.btc.model.Account;

import javax.sql.DataSource;
import java.sql.*; //TODO: avoid this, be explicit
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AccountDaoImpl implements AccountDao {

    @Inject
    DataSource dataSource;

    @Override
    public List<Account> getAllAccounts() {
        final String queryGetAllAccounts = "SELECT * FROM Account";
        List<Account> accountList = new ArrayList<>();
        try {
            ResultSet resultSet = executeQuery(queryGetAllAccounts);
            while (resultSet.next()) {
                accountList.add(prepareAccount(resultSet));
            }
            return accountList;
        } catch (Exception e) {
            //potentially handle differently
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public Account createAccount(Account account) {
        return null;
    }

    private Account prepareAccount(ResultSet resultSet) throws SQLException {
        return new Account()
                .withAccountNumber(resultSet.getLong(1))
                .withFirstName(resultSet.getString(2))
                .withLastName(resultSet.getString(3))
                .withBalance(resultSet.getLong(4));
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

    private ResultSet executeQuery(final String query) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }
}

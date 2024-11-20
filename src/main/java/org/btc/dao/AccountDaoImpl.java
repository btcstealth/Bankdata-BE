package org.btc.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.btc.gateway.ExchangeGatewayImpl;
import org.btc.model.Account;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class AccountDaoImpl implements AccountDao {

    private static final Logger logger = Logger.getLogger(ExchangeGatewayImpl.class.getName());

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

    private ResultSet executeQuery(final String query) throws SQLException {
        Connection connection = this.dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }


    @Override
    public Account createAccount(Account account) {
        //TODO: fix to be correct related to prepared statement
        final String queryCreateAccount =
                String.format("INSERT INTO account(account_number, first_name, last_name, balance, currency_unit)" +
                        " VALUES(%s, '%s', '%s', %s, '%s');",
                        account.getAccountNumber(),
                        account.getFirstName(),
                        account.getLastName(),
                        account.getBalance(),
                        "DKK"
                );
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(queryCreateAccount);
            int result = statement.executeUpdate();
            logger.info("Create request result: " + result);
            return account;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Account prepareAccount(ResultSet resultSet) throws SQLException {
        return new Account()
                .withBalance(resultSet.getDouble("balance"))
                .withAccountNumber(resultSet.getLong("account_number"))
                .withFirstName(resultSet.getString("first_name"))
                .withLastName(resultSet.getString("last_name"))
                .withCurrencyUnit(resultSet.getString("currency_unit"));
    }

    @Override
    public void depositIntoAccount(Long accountNumber, double fundsAmount) {
    }

    @Override
    public void transferFunds(Long senderAccountNumber, Long retrieverAccountNumber, double amount) {

    }

    @Override
    public Account getAccount(Long accountNumber) {
        final String queryGetAccountByAccountNumber = "SELECT * FROM Account WHERE account_number = ?";
        Account existingAccount = null;
        //TODO: refactor such that connection is closed after database request.
        try {
            ResultSet resultSet = executeQuery(queryGetAccountByAccountNumber, accountNumber);
            while (resultSet.next()) {
                existingAccount = prepareAccount(resultSet);
            }
            if (existingAccount == null) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            //potentially handle differently
            throw new RuntimeException(e.getMessage());
        }
        return existingAccount;
    }

    private ResultSet executeQuery(final String query, final Long accountNumber) throws SQLException {
        Connection connection = this.dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, accountNumber);
        ResultSet resultSet = statement.executeQuery();

        //TODO: refactor this
        statement.close();
        connection.close();
        return resultSet;
    }

    @Override
    public double getAccountBalance(Long accountNumber) throws SQLException {
        double accountBalance = 0.0;

        final String queryGetAccountByAccountNumber = "SELECT balance FROM Account WHERE account_number = ?";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(queryGetAccountByAccountNumber);
        statement.setLong(1, accountNumber);

        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultSet.getDouble("balance");
            }
        } catch (Exception e) {
            //potentially handle differently
            throw new RuntimeException(e.getMessage());
        } finally {
            statement.close();
        }
        return accountBalance;
    }
}

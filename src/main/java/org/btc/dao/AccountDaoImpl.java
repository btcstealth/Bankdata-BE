package org.btc.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.btc.gateway.ExchangeGatewayImpl;
import org.btc.model.Account;

import javax.sql.DataSource;
import java.sql.*; //TODO: avoid this, be explicit
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
        final String queryCreateAccount =
                String.format("INSERT INTO account(accountNumber, firstName, lastName, balance, currencyUnit)" +
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
                .withBalance(resultSet.getDouble(1))
                .withAccountNumber(resultSet.getLong(2))
                .withFirstName(resultSet.getString(4))
                .withLastName(resultSet.getString(5))
                .withCurrencyUnit(resultSet.getString(3));
    }

    @Override
    public void depositIntoAccount(Long accountNumber, double fundsAmount) {
    }

    @Override
    public void transferFunds(Long senderAccountNumber, Long retrieverAccountNumber, double amount) {

    }

    @Override
    public Account getAccount(Long accountNumber) {
        final String queryGetAccountByAccountNumber = "SELECT * FROM Account WHERE accountNumber = ?";
        Account existingAccount = null;
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
        return statement.executeQuery();
    }

    @Override
    public double getAccountBalance(Long accountNumber) throws SQLException {
        double accountBalance = 0.0;

        final String queryGetAccountByAccountNumber = "SELECT * FROM Account WHERE accountNumber = ?";
        Connection connection = this.dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(queryGetAccountByAccountNumber);
        statement.setLong(1, accountNumber);

        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultSet.getLong(1);
                resultSet.getString(2);
                resultSet.getString(3);
                resultSet.getString(4);
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

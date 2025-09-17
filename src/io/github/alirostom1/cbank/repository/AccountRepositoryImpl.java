package io.github.alirostom1.cbank.repository;

import io.github.alirostom1.cbank.model.entity.CheckingsAccount;
import io.github.alirostom1.cbank.model.entity.SavingsAccount;
import io.github.alirostom1.cbank.util.DatabaseConnection;
import java.sql.*;
import io.github.alirostom1.cbank.repository.Interface.AccountRepositoryInterface;
import io.github.alirostom1.cbank.model.entity.Account;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepositoryInterface{
    private final Connection connection;

    public AccountRepositoryImpl(){
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    
    public boolean store(Account account) throws SQLException{
        String query = "INSERT INTO accounts(code,balance,account_type) values (?,?,?)";
        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1,account.getCode());
            stmt.setDouble(2,account.getBalance());
            if(account instanceof CheckingsAccount){
                stmt.setString(3,"checkings");
            }else{
                stmt.setString(3,"savings");
            }
            stmt.executeUpdate();
        }
        if(account instanceof CheckingsAccount){
            CheckingsAccount checkAcc = (CheckingsAccount) account;
            query = "INSERT INTO checkingsaccounts(code,overdraft_limit) values (?,?)";
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setString(1,checkAcc.getCode());
                stmt.setDouble(2,checkAcc.getOverdraftLimit());
                return stmt.executeUpdate() > 0;
            }
        }else if (account instanceof SavingsAccount){
            SavingsAccount saveAcc = (SavingsAccount) account;
            query = "INSERT INTO savingsaccounts(code,interest_rate) values(?,?)";
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setString(1,saveAcc.getCode());
                stmt.setDouble(2,saveAcc.getInterest());
                return stmt.executeUpdate() > 0;
                
            }
        }
        return false;
    }
    public boolean save(Account account) throws SQLException{
       String query = "Update accounts set balance = ? WHERE code = ?";
       try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setDouble(1,account.getBalance());
            stmt.setString(2,account.getCode());
            stmt.executeUpdate();
       }
       if(account instanceof CheckingsAccount){
           CheckingsAccount checkAcc = (CheckingsAccount) account;
           query = "Update checkingsaccounts set overdraft_limit = ? where code = ?";
           try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setDouble(1,checkAcc.getOverdraftLimit());
                stmt.setString(2,checkAcc.getCode());
                return stmt.executeUpdate() > 0;
           }
       }else if(account instanceof SavingsAccount){
           SavingsAccount saveAcc = (SavingsAccount) account;
           query = "Update savingsaccounts set interest_rate = ? where code = ?";
           try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setDouble(1,saveAcc.getInterest());
                stmt.setString(2,saveAcc.getCode());
                return stmt.executeUpdate() > 0;
           }
       }
       return false;
    }
    public Optional<Account> findByCode(String code) throws SQLException{
        String query = "SELECT * FROM accounts where code = ?";
        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1,code);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                if(rs.getString("account_type").equals("Savings")){
                    SavingsAccount saveAcc = new SavingsAccount(code,rs.getDouble("balance")); 
                    query = "SELECT * FROM savingsaccounts where code = ?";
                    try(PreparedStatement stmt2 = connection.prepareStatement(query)){
                        stmt2.setString(1,code);
                        ResultSet rs2 = stmt2.executeQuery();
                        if(rs2.next()){
                            saveAcc.setInterest(rs2.getDouble("interest_rate"));
                            return Optional.of(saveAcc);
                        }
                    }
                }else if(rs.getString("account_type").equals("checkings")){
                    CheckingsAccount checkAcc = new CheckingsAccount(code,rs.getDouble("balance"));
                    query = "SELECT * from checkingsaccounts where code = ?";
                    try(PreparedStatement stmt2 = connection.prepareStatement(query)){
                        stmt2.setString(1,code);
                        ResultSet rs2 = stmt2.executeQuery();
                        if(rs2.next()){
                            checkAcc.setOverdraftLimit(rs2.getDouble("overdraft_limit"));
                            return Optional.of(checkAcc);
                        }
                    }
                }

            }
            return Optional.empty();
        }
    }
}

package io.github.alirostom1.cbank.repository;

import io.github.alirostom1.cbank.repository.Interface.OperationRepositoryInterface;
import io.github.alirostom1.cbank.model.entity.Operation; 
import java.sql.SQLException;
import java.sql.PreparedStatement;
import io.github.alirostom1.cbank.model.enums.*;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import io.github.alirostom1.cbank.model.entity.*;
import java.sql.ResultSet;
import java.util.ArrayList; 

public class OperationRepositoryImpl implements OperationRepositoryInterface{
    private final Connection connection;
    

    public OperationRepositoryImpl(Connection connection){
        this.connection = connection;
    }

    public boolean store(Operation op) throws SQLException{
        String query = "INSERT INTO operations(id,date,amount,operation_type,code) values(?,?,?,?,?) ";
        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1,op.getId().toString());
            stmt.setTimestamp(2,Timestamp.valueOf(op.getDate()));
            stmt.setDouble(3,op.getAmount());
            if(op instanceof Withdrawal){
                stmt.setString(4,"withdrawal");
            }else if(op instanceof Deposit){
                stmt.setString(4,"deposit");
            }
            stmt.setString(5,op.getCode());
            stmt.executeUpdate();
        }
        if(op instanceof Withdrawal){
            Withdrawal w = (Withdrawal) op;
            query = "INSERT INTO withdrawals(id,destination) values(?,?)";
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setString(1,w.getId().toString());
                stmt.setString(2,w.getDestination().name());
                return stmt.executeUpdate() > 0;
            }
        }else if(op instanceof Deposit ){
            Deposit d = (Deposit) op;
            query = "INSERT INTO deposits(id,source) values(?,?)";
            try(PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setString(1,d.getId().toString());
                stmt.setString(2,d.getSource().name());
                return stmt.executeUpdate() > 0;
            }
        }
        return false;
    }
    public List<Operation> getAll(String code) throws SQLException{
        String query = "SELECT * FROM operations where code = ?";
        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1,code);
            ResultSet rs = stmt.executeQuery();
            List<Operation> ops = new ArrayList<>();
            while(rs.next()){
                if(rs.getString("operation_type").equals("withdrawal")){
                    query = "Select * from withdrawals where id = ? ";
                    try(PreparedStatement stmt2 = connection.prepareStatement(query)){
                        
                        stmt2.setString(1,rs.getString("id"));
                        ResultSet rs2 = stmt2.executeQuery();
                        rs2.next();
                        Withdrawal op = new Withdrawal(
                                                rs.getString("id"),
                                                rs.getTimestamp("date").toLocalDateTime(),
                                                rs.getDouble("amount"),
                                                rs.getString("code"),
                                                Destination.valueOf(rs2.getString("destination"))
                                            );
                                            ops.add(op);  
                                        }
                                        
                }else{
                    query = "Select * from deposits where id = ? ";
                    try(PreparedStatement stmt2 = connection.prepareStatement(query)){
                        stmt2.setString(1,rs.getString("id"));
                        System.out.println("in");
                        ResultSet rs2 = stmt2.executeQuery();
                        rs2.next();
                        Deposit op = new Deposit(
                                            rs.getString("id"),
                                            rs.getTimestamp("date").toLocalDateTime(),
                                            rs.getDouble("amount"),
                                            rs.getString("code"),
                                            Source.valueOf(rs2.getString("source"))
                                    );
                        ops.add(op);
                    }
                }
            }
        return ops;
        }
    } 
}

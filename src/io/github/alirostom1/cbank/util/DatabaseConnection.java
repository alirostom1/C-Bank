package io.github.alirostom1.cbank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection{
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cbank","root","123456789");
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return this.connection;
    }
    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
}

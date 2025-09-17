package io.github.alirostom1.cbank;

import io.github.alirostom1.cbank.util.DatabaseConnection;
import java.sql.Connection;
import io.github.alirostom1.cbank.repository.*;
import io.github.alirostom1.cbank.repository.Interface.*;
import io.github.alirostom1.cbank.model.entity.*;
import io.github.alirostom1.cbank.service.*;
import io.github.alirostom1.cbank.service.Interface.*;
import java.sql.SQLException;
import java.util.Random;
import java.util.Optional;
import java.util.Scanner;
import io.github.alirostom1.cbank.ui.*;


public final class Main{
    public static void main(String[] args){
        Connection connection = DatabaseConnection.getInstance().getConnection();
        AccountRepositoryInterface accountRepository = new AccountRepositoryImpl(connection);
        AccountServiceInterface accountService = new AccountServiceImpl(accountRepository);
        AccountUI accountUI = new AccountUI(accountService);
        MainMenuUI mainmenu = new MainMenuUI(accountUI);
        try{
            mainmenu.run();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
    }
}

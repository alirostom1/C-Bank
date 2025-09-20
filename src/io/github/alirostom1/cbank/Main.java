package io.github.alirostom1.cbank;

import io.github.alirostom1.cbank.util.DatabaseConnection;
import java.sql.Connection;
import io.github.alirostom1.cbank.repository.*;
import io.github.alirostom1.cbank.repository.Interface.*;
import io.github.alirostom1.cbank.service.*;
import io.github.alirostom1.cbank.service.Interface.*;
import io.github.alirostom1.cbank.ui.*;


public final class Main{
    public static void main(String[] args){
        Connection connection = DatabaseConnection.getInstance().getConnection();
        AccountRepositoryInterface accountRepository = new AccountRepositoryImpl(connection);
        OperationRepositoryInterface operationRepository = new OperationRepositoryImpl(connection);
        OperationServiceInterface operationService = new OperationServiceImpl(operationRepository,accountRepository);
        AccountServiceInterface accountService = new AccountServiceImpl(accountRepository);
        OperationUI operationUI = new OperationUI(operationService);
        AccountUI accountUI = new AccountUI(accountService);
        MainMenuUI mainMenuUI = new MainMenuUI(accountUI,operationUI);
        try{
            mainMenuUI.run();    
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
    }
}

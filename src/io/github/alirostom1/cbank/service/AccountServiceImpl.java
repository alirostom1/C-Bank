package io.github.alirostom1.cbank.service;

import io.github.alirostom1.cbank.service.Interface.AccountServiceInterface;
import io.github.alirostom1.cbank.repository.AccountRepositoryImpl;
import io.github.alirostom1.cbank.repository.Interface.AccountRepositoryInterface;
import io.github.alirostom1.cbank.model.entity.SavingsAccount;
import io.github.alirostom1.cbank.model.entity.CheckingsAccount;
import io.github.alirostom1.cbank.util.Generator;
import java.sql.SQLException;

public class AccountServiceImpl implements AccountServiceInterface{
    private final AccountRepositoryInterface accountRepository; 
    

    public AccountServiceImpl(AccountRepositoryInterface accountRepository){
        this.accountRepository = accountRepository;
    }

    public boolean createAccount(String type){
        String code = Generator.generateCode();
        if(type.equals("savings")){
            SavingsAccount saveAcc = new SavingsAccount(code,0.00);
            try{
                accountRepository.store(saveAcc);
                return true;
            }catch(SQLException e){
                return false;
            }
        }else if(type.equals("checkings")){
            CheckingsAccount checkAcc = new CheckingsAccount(code,0.00);
            try{
                accountRepository.store(checkAcc);
                return true;
            }catch(SQLException e){
                return false;
            }
        }
        return false;
    }
}

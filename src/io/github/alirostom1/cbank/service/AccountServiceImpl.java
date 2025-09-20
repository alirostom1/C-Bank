package io.github.alirostom1.cbank.service;

import io.github.alirostom1.cbank.service.Interface.AccountServiceInterface;
import io.github.alirostom1.cbank.repository.Interface.AccountRepositoryInterface;
import io.github.alirostom1.cbank.model.entity.SavingsAccount;
import io.github.alirostom1.cbank.model.entity.CheckingsAccount;
import io.github.alirostom1.cbank.util.Generator;
import java.sql.SQLException;
import java.util.Optional;

public class AccountServiceImpl implements AccountServiceInterface{
    private final AccountRepositoryInterface accountRepository; 
    

    public AccountServiceImpl(AccountRepositoryInterface accountRepository){
        this.accountRepository = accountRepository;
    }

    public Optional<String> createAccount(String type){
        String code = Generator.generateCode();
        if(type.equals("savings")){
            SavingsAccount saveAcc = new SavingsAccount(code,0.00);
            try{
                accountRepository.store(saveAcc);
                return Optional.of(code);
            }catch(SQLException e){
                return Optional.empty();
            }
        }else if(type.equals("checkings")){
            CheckingsAccount checkAcc = new CheckingsAccount(code,0.00);
            try{
                accountRepository.store(checkAcc);
                return Optional.of(code);
            }catch(SQLException e){
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}

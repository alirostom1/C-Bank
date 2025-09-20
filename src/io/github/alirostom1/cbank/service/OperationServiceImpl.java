package io.github.alirostom1.cbank.service;

import io.github.alirostom1.cbank.service.Interface.OperationServiceInterface;
import io.github.alirostom1.cbank.repository.Interface.OperationRepositoryInterface;
import io.github.alirostom1.cbank.repository.Interface.AccountRepositoryInterface; 
import io.github.alirostom1.cbank.model.entity.Operation;
import io.github.alirostom1.cbank.model.entity.Account;
import io.github.alirostom1.cbank.model.entity.CheckingsAccount;
import io.github.alirostom1.cbank.model.entity.Withdrawal;
import io.github.alirostom1.cbank.model.enums.Destination;
import io.github.alirostom1.cbank.model.enums.Source;
import io.github.alirostom1.cbank.model.entity.Deposit;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


public class OperationServiceImpl implements OperationServiceInterface{
    private final OperationRepositoryInterface opRepo;
    private final AccountRepositoryInterface accRepo; 

    public OperationServiceImpl(OperationRepositoryInterface opRepo,AccountRepositoryInterface accRepo){
        this.opRepo = opRepo;
        this.accRepo = accRepo;
    }
    
    public boolean depositOrWithdraw(Operation op) throws Exception{
        Optional<Account> account = accRepo.findByCode(op.getCode());
        if(account.isPresent() == false){
            throw new Exception("Bank account not found !");
        }
        if(op.getAmount() < 0){
            throw new Exception("the amount should be positive !");
        }
        Account acc = account.get();
        if(op instanceof Withdrawal){
            if(acc instanceof CheckingsAccount){
                CheckingsAccount checkacc = (CheckingsAccount) acc; 
                if(op.getAmount() > checkacc.getBalance() + checkacc.getOverdraftLimit()){
                    throw new Exception("Insufficient funds ! Your balance  = " + checkacc.getBalance() +" + overdraft: " + checkacc.getOverdraftLimit() );
                }
                checkacc.setBalance(checkacc.getBalance() - op.getAmount());
                try{
                    accRepo.save(checkacc);
                    opRepo.store(op);
                    return true;
                }catch(Exception e){
                    throw e;
                }
                
            }else{
                if(op.getAmount() > acc.getBalance()){
                       throw new Exception("Insufficient funds ! Your balance  = " + acc.getBalance() +" ");
                   }
                   acc.setBalance(acc.getBalance() - op.getAmount());
                   try{
                       accRepo.save(acc);
                       opRepo.store(op);
                       return true;
                   }catch(Exception e){
                       throw new Exception("Internal problem try again later !");
                   }
            }
        }else{
            acc.setBalance(acc.getBalance() + op.getAmount());
            accRepo.save(acc);
            opRepo.store(op);
            return true;
        }
    }
    public boolean Transfer(String wcode,String dcode,double amount) throws Exception{
        if(wcode.equals(dcode)){
            throw new Exception("You cannot transfer money to the same account !");
        }
        Optional<Account> waccount = accRepo.findByCode(wcode);
        if(waccount.isPresent() == false){
            throw new Exception("Sender account not found !");
        }
        Optional<Account> daccount = accRepo.findByCode(dcode);
        if(daccount.isPresent() == false){
            throw new Exception("Recipient account not found !");
        }
        if(amount < 0){
            throw new Exception("the amount should be positive !");
        }
        Withdrawal withdrawal = new Withdrawal(amount,wcode,Destination.TRANSFER_OUT);
        Deposit deposit = new Deposit(amount,dcode,Source.TRANSFER_IN);
        try{
            this.depositOrWithdraw(withdrawal);
            this.depositOrWithdraw(deposit);
            return true;
        }catch(Exception e){
            throw e;
        }
    }
    public List<Operation> getAll(String code) throws Exception{
        try{
            return opRepo.getAll(code);
        }catch(Exception e){
            throw e;
        }
    }
 


}

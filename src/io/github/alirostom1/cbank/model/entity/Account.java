package io.github.alirostom1.cbank.model.entity;


public abstract class Account{
    protected String code;
    protected Double balance;
    
    public Account(String code,Double balance){
        this.code = code;
        this.balance = balance;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public Double getBalance(){
        return this.balance;
    }
    public void setBalance(Double balance){
        this.balance = balance;
    }
    public abstract boolean withdraw(double amount);
    public abstract double calculateInterest();
    public abstract void displayDetails();
}

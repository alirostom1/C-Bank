package io.github.alirostom1.cbank.model.entity;

public class CheckingsAccount extends Account{
    private double overdraftLimit;

    public CheckingsAccount(String code,double balance){
        super(code,balance);
        this.overdraftLimit = 500.00;
    }
    public CheckingsAccount(String code,double balance,double overdraftLimit){
        super(code,balance);
        this.overdraftLimit = overdraftLimit;
    }
    public void setOverdraftLimit(double overdraftLimit){
        this.overdraftLimit = overdraftLimit;
    }
    public double getOverdraftLimit(){
        return this.overdraftLimit;
    }
    
    public boolean withdraw(double amount){
        if(amount > (overdraftLimit + balance)){
            return false;
        }else{
            balance -= amount;
            return true;
        }
    } 
    public double calculateInterest(){
        return 0;
    }
    public void displayDetails(){
        System.out.println("type  : Checkings acouunt");
        System.out.println("Code : " + code);
        System.out.println("balance : " + balance);
        System.out.println("overdraft limit : " + overdraftLimit);
    }
}

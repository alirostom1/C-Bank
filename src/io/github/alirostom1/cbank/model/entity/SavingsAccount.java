
package io.github.alirostom1.cbank.model.entity;

public class SavingsAccount extends Account{
    private double interest;

    public SavingsAccount(String code,double balance,double interest){
        super(code,balance);
        this.interest = interest;
    }
    public SavingsAccount(String code,double balance){
        super(code,balance);
        this.interest = 0.05;
    }

    public void setInterest(double interest){
        this.interest = interest;
    }
    public double getInterest(){
        return this.interest;
    }

    public boolean withdraw(double amount){
        if(amount > balance){
            return false;
        }else{
            balance -= amount;
            return true;
        }
    }
    public double calculateInterest(){
        return balance * interest;
    }
    public void displayDetails(){
        System.out.println("type : Savings");
        System.out.println("Code : " + code);
        System.out.println("balance : " + balance);
        System.out.println("interest rate : " + interest);
    }
}

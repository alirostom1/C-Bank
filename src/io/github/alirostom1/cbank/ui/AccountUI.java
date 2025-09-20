package io.github.alirostom1.cbank.ui;

import io.github.alirostom1.cbank.model.entity.Account;
import io.github.alirostom1.cbank.service.Interface.AccountServiceInterface;

import java.util.Optional;
import java.util.Scanner;


public class AccountUI{
    private final AccountServiceInterface accountService;
    private final Scanner scanner = new Scanner(System.in);

    public AccountUI(AccountServiceInterface accountService){
        this.accountService = accountService;  
    }
    public void run() throws InterruptedException{
        while(true){
            System.out.println("***********Account Menu***********");
            System.out.println("**1. Create account");
            System.out.println("**2. Display my account details");
            System.out.println("**3. Return to main menu ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    createAccount();
                    break;
                case 2:
                    displayAccountDetails();
                    break;
                case 3 : 
                    System.out.println("Returning...");
                    Thread.sleep(3000);
                    return;
                default:
                    break;
            }
        }
    }
    public void createAccount(){
        System.out.println("Please enter the type of the account (savings/checkings): ");
        String type = scanner.nextLine().toLowerCase();
        String code = accountService.createAccount(type).get();
        System.out.println("This is your account code : " + code);
        System.out.println("Please remember it and note it down!");
        System.out.println("Press Anything to move further !");
        scanner.nextLine();
        return;
    }
    public void displayAccountDetails(){
        System.out.println("Please enter your account code: ");
        String accCode = scanner.nextLine();
        if(accCode.isEmpty()){
            System.out.println("Account code cannot be empty!");
            return;
        }
        Optional<Account> accountOpt = accountService.getAccountByCode(accCode);
        if(accountOpt.isPresent()){
            Account account = accountOpt.get();
            account.displayDetails();
            System.out.println("Press Anything to move further !");
            scanner.nextLine();
            return;
        }else{
            System.out.println("Account not found!");
            System.out.println("Press Anything to move further !");
            scanner.nextLine();
            return;
        }
    }
}

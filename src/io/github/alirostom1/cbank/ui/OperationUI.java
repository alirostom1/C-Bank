package io.github.alirostom1.cbank.ui;

import java.util.List;
import java.util.Scanner;

import io.github.alirostom1.cbank.model.enums.Destination;

import io.github.alirostom1.cbank.model.entity.Deposit;
import io.github.alirostom1.cbank.model.entity.Operation;
import io.github.alirostom1.cbank.model.entity.Withdrawal;
import io.github.alirostom1.cbank.model.enums.Source;
import io.github.alirostom1.cbank.service.Interface.OperationServiceInterface;


public class OperationUI{
    private final OperationServiceInterface opService;
    private final Scanner scanner = new Scanner(System.in); 

    public OperationUI(OperationServiceInterface opService){
        this.opService = opService;
    }
    
    public void run() throws InterruptedException{
        while(true){
            System.out.println("***********Operation Menu***********");
            System.out.println("**1. Make a deposit");
            System.out.println("**2. Make a withdraw");
            System.out.println("**3. Send money to another account");
            System.out.println("**4. Display my operations");
            System.out.println("**5. Return to main menu ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    displayOperations();
                    break;
                case 5 : 
                    System.out.println("Returning...");
                    Thread.sleep(3000);
                    return;
                default:
                    break;
            }

        }
    }
    public void deposit(){
        System.out.println("Please enter your account code: ");
        String accCode = scanner.nextLine();
        if(accCode.isEmpty()){
            System.out.println("Account code cannot be empty!");
            return;
        }
        System.out.println("Please enter the amount you want to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.println("Please enter the source of the deposit:(SALARY/CASH) ");
        String sourceInput = scanner.nextLine();
        if(!sourceInput.equalsIgnoreCase("SALARY") && !sourceInput.equalsIgnoreCase("CASH")){
            System.out.println("Invalid source type! Please enter either 'SALARY' or 'CASH'.");
            return;
        }
        Deposit deposit = new Deposit(amount,accCode,Source.valueOf(sourceInput.toUpperCase()));
        try{
            boolean success = opService.depositOrWithdraw(deposit);
            if(success){
                System.out.println("Deposit successful!");
            }else{
                System.out.println("Deposit failed! Please check your account code and try again.");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Press Anything to move further !");
        scanner.nextLine();
        return;
    }
    public void withdraw(){
        System.out.println("Please enter your account code: ");
        String accCode = scanner.nextLine();
        System.out.println("Please enter the amount you want to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.println("Please enter the destination of the withdraw:(ATM/CHECK) ");
        String destinationInput = scanner.nextLine();
        Withdrawal withdrawal = new Withdrawal(amount,accCode,Destination.valueOf(destinationInput));
        try{
            boolean success = opService.depositOrWithdraw(withdrawal);
            if(success){
                System.out.println("Deposit successful!");
            }else{
                System.out.println("Deposit failed! Please check your account code and try again.");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Press Anything to move further !");
        scanner.nextLine();
        return;
    }
    public void transfer(){
        System.out.println("Please enter your account code: ");
        String wcode = scanner.nextLine();
        System.out.println("Please enter the recipient account code: ");
        String dcode = scanner.nextLine();
        System.out.println("Please enter the amount you want to transfer: ");
        double amount = Double.parseDouble(scanner.nextLine());
        try{
            boolean success = opService.Transfer(wcode,dcode,amount);
            if(success){
                System.out.println("Transfer successful!");
            }else{
                System.out.println("Transfer failed! Please check your account code and try again.");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Press Anything to move further !");
        scanner.nextLine();
        return;
    }
    public void displayOperations(){
        System.out.println("Please enter your account code: ");
        String accCode = scanner.nextLine();
        if(accCode.isEmpty()){
            System.out.println("Account code cannot be empty!");
            return;
        }
        try{
            List<Operation> operations = opService.getAll(accCode);
            if(operations.isEmpty()){
                System.out.println("No operations found for this account.");
            }else{
                System.out.println("Operations for account " + accCode + ":");
                for(Operation op : operations){
                    if(op instanceof Deposit){
                        Deposit dep = (Deposit) op;
                        System.out.println("Deposit of " + dep.getAmount() + " on " + dep.getDate() + " from " + dep.getSource());
                    }else if(op instanceof Withdrawal){
                        Withdrawal wit = (Withdrawal) op;
                        System.out.println("Withdrawal of " + wit.getAmount() + " on " + wit.getDate() + " to " + wit.getDestination());
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Press Anything to move further !");
        scanner.nextLine();
        return;
    }

}

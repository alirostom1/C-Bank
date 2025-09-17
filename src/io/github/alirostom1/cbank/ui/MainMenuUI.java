package io.github.alirostom1.cbank.ui;

import java.util.Scanner;

public class MainMenuUI{
    private final Scanner scanner = new Scanner(System.in);
    private final AccountUI accountUI;

    public MainMenuUI(AccountUI accountUI){
        this.accountUI = accountUI;
    }

    public void run() throws InterruptedException{
        while(true){
            System.out.println("***********C-Bank Menu***********");
            System.out.println("*****1. Account Management ");
            System.out.println("*****2. Operations Management ");
            System.out.println("*****3. Quit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1 :
                    accountUI.run();
                    break;
                case 2 :
                    break;
                case 3:
                    System.out.println("Exiting...");
                    Thread.sleep(3000);
                    return;
                default:
                    break;
            }
        }
    } 
}

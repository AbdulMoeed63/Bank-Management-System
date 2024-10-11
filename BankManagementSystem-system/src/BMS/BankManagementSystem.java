
package BMS;

import java.util.Scanner;

public class BankManagementSystem {
    
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to my Bank :");
            System.out.println("1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Update Account");
            System.out.println("4. Search Account");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Transfer");
            System.out.println("8. Check Balance");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (choice) {
                    case 1:
                        createAccount(scanner);
                        break;
                    case 2:
                        deleteAccount(scanner);
                        break;
                    case 3:
                        updateAccount(scanner);
                        break;
                    case 4:
                        searchAccount(scanner);
                        break;
                    case 5:
                        deposit(scanner);
                        break;
                    case 6:
                        withdraw(scanner);
                        break;
                    case 7:
                        transfer(scanner);
                        break;
                    case 8:
                        checkBalance(scanner);
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        
        System.out.print("Enter Account Number: ");
        
        String accountNumber = scanner.nextLine();
        
        System.out.print("Enter Name: ");
        
        String name = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        
        double balance = scanner.nextDouble();
        System.out.print("Enter Account Type (1 for Savings, 2 for Current): ");
        int type = scanner.nextInt();
        

        if (type == 1) {
            System.out.print("Enter Interest Rate: ");
            
            
            double interestRate = scanner.nextDouble();
            bank.createAccount(new SavingsAccount(accountNumber, name, balance, interestRate));
        } 
        
        else if (type == 2) {
            bank.createAccount(new CurrentAccount(accountNumber, name, balance));
        } 
        
        else {
            System.out.println("Invalid account type.");
        }
    }

    private static void deleteAccount(Scanner scanner) throws AccountNotFoundException {
        System.out.print("Enter Account Number to Delete: ");
        String accountNumber = scanner.nextLine();
        bank.deleteAccount(accountNumber);
    }

    private static void updateAccount(Scanner scanner) throws AccountNotFoundException {
        System.out.print("Enter Account Number to Update: ");
        
        String accountNumber = scanner.nextLine();
        
        System.out.print("Enter New Name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter New Balance: ");
        
        double newBalance = scanner.nextDouble();

        Double interestRate = null;
        
        Account account = bank.searchAccount(accountNumber);
        
        if (account instanceof SavingsAccount) {
            System.out.print("Enter New Interest Rate: ");
            interestRate = scanner.nextDouble();
        }
        bank.updateAccount(accountNumber, newName, newBalance, interestRate);
    }

    private static void searchAccount(Scanner scanner) throws AccountNotFoundException {
        System.out.print("Search by 1) Account Number or 2) Name: ");
        int searchType = scanner.nextInt();
        scanner.nextLine(); 

        if (searchType == 1) {
            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.nextLine();
            Account account = bank.searchAccount(accountNumber);
            System.out.println(account);
        } else if (searchType == 2) {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            for (Account account : bank.searchAccountByName(name)) {
                System.out.println(account);
            }
        } else {
            System.out.println("Invalid search type.");
        }
    }

    private static void deposit(Scanner scanner) throws AccountNotFoundException {
        System.out.print("Enter Account Number to Deposit: ");
        String accountNumber = scanner.nextLine();
        
        System.out.print("Enter Deposit Amount: ");
        double amount = scanner.nextDouble();
        bank.deposit(accountNumber, amount);
    }

    private static void withdraw(Scanner scanner) throws AccountNotFoundException {
        System.out.print("Enter Account Number to Withdraw: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Withdrawal Amount: ");
        double amount = scanner.nextDouble();
        bank.withdraw(accountNumber, amount);
    }

    private static void transfer(Scanner scanner) throws AccountNotFoundException {
        System.out.print("Enter Source Account Number: ");
        String fromAccountNumber = scanner.nextLine();
        
        System.out.print("Enter Destination Account Number: ");
        String toAccountNumber = scanner.nextLine();
        System.out.print("Enter Transfer Amount: ");
        
        
        double amount = scanner.nextDouble();
        
        bank.transfer(fromAccountNumber, toAccountNumber, amount);
    }

    private static void checkBalance(Scanner scanner) throws AccountNotFoundException {
        System.out.print("Enter Account Number to Check Balance: ");
        String accountNumber = scanner.nextLine();
        
        double balance = bank.checkBalance(accountNumber);
        
        System.out.println("Current Balance: " + balance);
    }
}

